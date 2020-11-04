package com.example.demo.util;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: chenwei
 * @data: 2020/10/28 21:53
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Resource
    AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    public boolean signature(String time, String version, String guid, String param, String signature){

        return false;
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setContentType("application/json;charset=utf-8");
        logger.info("time:" + request.getHeader("time"));
//        logger.info("guid:" + request.getHeader("guid"));
        logger.info("param:" + request.getHeader("param"));
        logger.info("signature:" + request.getHeader("signature"));
        logger.error("signature:" + request.getHeader("signature"));

//        logger2.trace("logger2.trace:" + request.getHeader("signature"));
//        logger2.debug("logger2.debug:" + request.getHeader("signature"));
//        logger2.info("logger2.info:" + request.getHeader("signature"));
//        logger2.error("logger2.error:" + request.getHeader("signature"));
        String signature = request.getHeader("signature");

        logger.info("request.getRequestURI(): " + request.getRequestURI());
        //得到path
        String path = request.getRequestURI();
        //得到param
        Map<String, String> returnMap = new HashMap<>();
        Set<Map.Entry<String, String[]>> entries = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            StringBuffer value = new StringBuffer("");
            String[] val = entry.getValue();
            if (null != val && val.length > 0) {
                for (String v:val) {
                    value.append(v);
                }
            }
            returnMap.put(key, value.toString());
        }
        //得到param
        String param = returnMap.toString();
        //对param进行处理，格式与前端要对应
        String regex = "\\=";
        param = param.replaceAll(regex,":");
        String regex2 = "\\s";
        param = param.replaceAll(regex2,"");
        //得到guid，从cookie中获取
        String guid = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uuid")) {
                    guid = cookie.getValue();
                }
            }
        }
        logger.info("guid: " + guid);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //serverTime
        String serverTime = formatter.format(date);

        //得到time
        String time = request.getHeader("time");

        //得到javaCryptToken
        // hash加密，加密规则，取uuid的2、5、8位组成一个十六进制的数，再将16进制的数转换为10进制，对其求余8得到一个数字num，对应hash表的num个表，eg:arr[num]，再取uuid中的arr[num]作为javaCryptToken
        int [][] arr=new int[][]{{0, 5, 9, 15, 22, 28},{2, 8, 19, 25, 30, 31},{20, 25, 31, 3, 4, 8}
        ,{25, 31, 0, 9, 13, 17},{29, 2, 11, 17, 21, 26},{10, 15, 18, 29, 2, 3}
        ,{5, 10, 15, 17, 18, 22},{8, 20, 22, 27, 19, 21}};

        String s = String.valueOf(guid.charAt(2))+String.valueOf(guid.charAt(5))+String.valueOf(guid.charAt(8));
        logger.info("s:"+s);
        int num = Integer.parseInt(s, 16)%8;
        logger.info("num:"+num);
        String javaCryptToken = "";
        for (int i = 0;i<arr[num].length;i++){
            javaCryptToken += String.valueOf(guid.charAt(arr[num][i]));
        }
        logger.info(javaCryptToken);
        //md5(time+path+guid+param+javaCryptToken)
        String result = time + path + guid + param + javaCryptToken;
        logger.info("result:" + result);
        String javaSignature = DigestUtils.md5DigestAsHex(result.getBytes());
        logger.info("javaSignature: "+javaSignature);

        //请求的五要素不能为空
        if(time == null || path == null || path == null  || guid == null || param == null || javaSignature == null){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("code", "551");
            json.put("message", "请求五要素不能为空");
            writer.print(json);
            writer.close();
            return false;
        }
        logger.info("time:   "+time);
        logger.info("serverTime:   "+serverTime);

        logger.info("formatterSs.format(serverTime):   "+serverTime.substring(serverTime.length()-3,serverTime.length()));

        String clientTimeString = time.substring(time.length()-2,time.length());
        String serverTimeString = serverTime.substring(serverTime.length()-2,serverTime.length());
//        logger.info("clientTimeString:" +clientTimeString);
//        logger.info("serverTimeString:" +serverTimeString);

        int clientTimeInt = Integer.parseInt(clientTimeString);
        int serverTimeInt = Integer.parseInt(serverTimeString);

        logger.info("clientTimeInt: "+clientTimeInt);
        logger.info("serverTimeInt: "+serverTimeInt);

        if(serverTimeInt<clientTimeInt){
            serverTimeInt+=60;
        }
        //判断请求时间是否超时，时间在两秒内是正常的
        String clientTimeCutLastTwoChar = time.substring(0,time.length()-4);
        String serverTimeCutLastTwoChar = serverTime.substring(0,serverTime.length()-4);
        logger.info("clientTimeCutLastTwoChar: "+clientTimeCutLastTwoChar);
        logger.info("serverTimeCutLastTwoChar: "+serverTimeCutLastTwoChar);
        if(!serverTimeCutLastTwoChar.equals(clientTimeCutLastTwoChar)){
            PrintWriter writer = null;
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("code", "552");
            json.put("message", "请求严重超过，你是黑客");
            writer.print(json);
            writer.close();
            return false;
        }else {
            if(serverTimeInt-clientTimeInt >2){
                PrintWriter writer = null;
                writer = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("code", "553");
                json.put("message", "请求时间超过");
                writer.print(json);
                writer.close();
                return false;
            }
        }

        //判断uuid是否存在，不存在拦截掉
        Admin admin = new Admin();
        admin.setUuid(guid);
        if(adminService == null ){
            logger.info("adminService == null");
        }else {
            logger.info("adminService != null");
        }
        List<Admin> adminList = new ArrayList<>();
        adminList = adminService.queryAll(admin);
        logger.info("adminService.queryAll(admin).size():"+adminList.size());
        for(int i=0;i<adminList.size();i++){
            logger.info(adminList.get(i).getUuid());
        }
        //判断uuid是否存在
        if(adminList.size() == 0){
            PrintWriter writer = null;
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("code", "554");
            json.put("message", "管理员不存在");
            writer.print(json);
            writer.close();
            return false;
        }

        if(!javaSignature.equals(signature)){
            logger.error("!javaSignature.equals(signature)");
            PrintWriter writer = null;
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("code", "555");
            json.put("message", "签证验证失败!javaSignature.equals(signature)");
            writer.print(json);
            writer.close();
            return false;
        }

        return true;
    }
}

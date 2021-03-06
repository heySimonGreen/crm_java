package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Admin)表控制层
 *
 * @author chenwei
 * @since 2020-10-16 15:13:27
 */
@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Admin selectOne(Integer id) {
        return this.adminService.queryById(id);
    }

    @GetMapping("queryAllAdmin")
    public List queryAllAdmin() {
        return this.adminService.queryAllAdmin();
    }

    @GetMapping("login")
    public Object login(@RequestParam String username, @RequestParam String passwd){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPasswd(passwd);
        int correctUsernameAndPasswd = 1;
        if(adminService.loginSelectByEntity(admin).size() == 0){
            correctUsernameAndPasswd = 0;
        }
        List<Admin> adminList = adminService.loginSelectByEntity(admin);


        System.out.println("adminList..........");
        System.out.println(adminList);
        //返回的数据{"code":20000,"data":{"token":"admin-token"}}
        System.out.println(username + " " + passwd);
        Map<String, String> map2 = new HashMap<>();
        map2.put("token","admin-token");
        Map<String, String> data =map2;
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("data",map2);
        Map<Object, Object> map = new HashMap<>();
        map.put("code",20000);
        map.put("data",map2);
        map.put("username",username);
        map.put("correctUsernameAndPasswd",correctUsernameAndPasswd);
        if(adminService.loginSelectByEntity(admin).size() != 0){
            map.put("adminid",adminList.get(0).getId());
            map.put("uuid",adminList.get(0).getUuid());
        }
        return map;
    }

    //测试，post还是不行
    @PostMapping("loginpost")
    public String login(@RequestBody String data){
        System.out.println("loginpost..........");
        System.out.println(data);
        return "loginpost";
    }

    //测试，post还是不行
//    public String rigister(@RequestBody Map<String,String> data){
    @PostMapping("rigister")
    public String rigister(@RequestParam Map<String,String> data){
////    public String rigister(@RequestBody String password){
//        System.out.println(data.toString());
//        return "ssss";

        System.out.println("loginpost..........");
        String username = data.get("username");
        String password = data.get("password");
        String phonenumber = data.get("phonenumber");
        System.out.println(data.get("username"));
        System.out.println(data.get("password"));
        System.out.println(data.get("phonenumber"));

        Admin admin1 = new Admin();
        Admin admin2 = new Admin();
        admin1.setUsername(username);
        admin2.setPhonenumber(phonenumber);
        List<Admin> adminList = adminService.queryAll(admin1);
        List<Admin> adminList2 = adminService.queryAll(admin2);
        if(adminList.size() == 0 && adminList2.size() == 0){
            String UUID = java.util.UUID.randomUUID().toString().replace("-","");
            Admin admin = new Admin();
            admin.setUuid(UUID);
            admin.setUsername(username);
            admin.setPasswd(password);
            admin.setPhonenumber(phonenumber);
            admin.setType(0);
            adminService.insert(admin);
            return "rigister successful";
        }else {
            return "username or phonenumber is exist";
        }
    }

}
package com.example.demo.controller;

import ch.qos.logback.core.net.QueueFactory;
import com.example.demo.dao.ContactpersonDao;
import com.example.demo.entity.Contactaddress;
import com.example.demo.entity.Contactperson;
import com.example.demo.entity.Customer;
import com.example.demo.service.ContactaddressService;
import com.example.demo.service.ContactpersonService;
import com.example.demo.service.CustomerService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.net.URLDecoder;

/**
 * (Customer)表控制层
 *
 * @author chenwei
 * @since 2020-09-22 23:41:58
 */
@RestController
//跨域只需要使用下面的代码
@CrossOrigin
@RequestMapping("customer")
@ResponseBody
public class CustomerController {
    /**
     * 服务对象
     */
    @Resource
    private CustomerService customerService;
    @Resource
    private  ContactpersonService contactpersonService;
    @Resource
    private ContactaddressService contactaddressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Customer selectOne(Integer cid) {
        return this.customerService.queryById(cid);
    }

    @GetMapping("selectAll")
    public List<Customer> selectAll() {
        return this.customerService.selectAll();
    }

//    返回所有客户信息并且含有客户联系人和联系地址
    @GetMapping("selectAllTest")
    public List selectAllTest() {
        List<Customer> customers = this.customerService.selectAll();
        List<Map<String, Object>> Lists = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < customers.size(); i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("contactaddress",contactaddressService.selectByCid(customers.get(i).getGuid()));
            map.put("guid", customers.get(i).getGuid());
            map.put("username",customers.get(i).getUsername());
            map.put("notes",customers.get(i).getNotes());
            System.out.println(customers.get(i).getGuid());
            System.out.println(customers.get(i).getUsername());
            System.out.println(customers.get(i).getNotes());
            map.put("contactperson", contactpersonService.selectByCid(customers.get(i).getGuid()));
            Lists.add(map);
        }
        return Lists;
    }


    @PostMapping("searchInputButton")
    public List searchInputButton(@RequestBody Map<String,String> data) {
        System.out.println("username..................................");
        System.out.println(data);
        System.out.println(data.get("input"));
        String username = data.get("input");
        List<Customer> customers = this.customerService.fuzzyQueryByName(username);

        List<Map<String, Object>> Lists = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < customers.size(); i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("contactaddress",contactaddressService.selectByCid(customers.get(i).getGuid()));
            map.put("guid", customers.get(i).getGuid());
            map.put("username",customers.get(i).getUsername());
            map.put("notes",customers.get(i).getNotes());
            System.out.println(customers.get(i).getGuid());
            System.out.println(customers.get(i).getUsername());
            System.out.println(customers.get(i).getNotes());
            map.put("contactperson", contactpersonService.selectByCid(customers.get(i).getGuid()));
            Lists.add(map);
        }
        return Lists;
    }


    //post方法不行，搞了半天只有选择get
    @PostMapping (value = "addCustomer2")
    //这里加@RequestBody会报错，不加不会报错，但同样得不到数据
    public String addCustomer2(@RequestBody Map<String,Object> data){
        System.out.println("guid...........");
        System.out.println(data.get("cid"));
        return "chenwei";
    }

    @PostMapping (value = "batchDeletAllCustomerByGuid")
    public String batchDeletAllCustomerByGuid(@RequestBody List<Integer> guid){
        System.out.println("guid...........");
        System.out.println(guid);
        for(int i=0;i<guid.size();i++){
            contactpersonService.deleteByCid(guid.get(i));
            contactaddressService.deleteByCid(guid.get(i));
            customerService.deleteById(guid.get(i));
        }
        return "batchDeletAllCustomerByGuid successful";
    }

//    @PostMapping(value = "addCustomer")
//    public String addCustomer(HttpServletRequest request, HttpServletResponse response){
//        System.out.println(request.getAttribute("username").toString());
//        return "chenwei";
//    }

    @GetMapping(value = "addCustomer", produces="application/json")
    //这个是以前没有解决不能提交post亲求时写的，很笨的办法，后面有时间了改为post肯定少很多代码，特别是对象直接转换为实体
    public Map<String, String> addCustomer(@RequestParam String customer,@RequestParam String contactaddress,@RequestParam String contactperson) throws UnsupportedEncodingException {
        //把传递过来的formData解码并放入map中
        final String charset = "utf-8";
        customer = URLDecoder.decode(customer, charset);
        Map<String, String> mapCustomer=new HashMap<String, String>();
        String[] arr1=customer.split("&");
        for (int i = 0; i <arr1.length; i++) {
            String key=arr1[i].substring(0, arr1[i].indexOf("="));
            String value=arr1[i].substring( arr1[i].indexOf("=")+1);
            mapCustomer.put(key, value);
        }
        System.out.println(mapCustomer);
        //添加客户信息username、gender
        Customer customer1 = new Customer();
        customer1.setNotes("notes");
        customer1.setUsername(mapCustomer.get("username"));
        customerService.insert(customer1);

        //批量添加联系人
        contactperson = URLDecoder.decode(contactperson, charset);
        Map<String, String> mapcontactperson=new LinkedHashMap<String, String>();
        String[] arr3=contactperson.split("&");
        for (int i = 0; i <arr3.length; i++) {
            String key=arr3[i].substring(0, arr3[i].indexOf("="));
            String value=arr3[i].substring( arr3[i].indexOf("=")+1);
            mapcontactperson.put(key, value);
        }
        System.out.println(mapcontactperson);
        List<String> cotactPersonL = new ArrayList<String>();
        for(Object key : mapcontactperson.keySet()){
            String value = mapcontactperson.get(key);
            cotactPersonL.add(value);
            System.out.println(value);
        }
        System.out.println("cotactPersonL.size().....................==="+cotactPersonL.size());
        for(int j=0;j<(cotactPersonL.size())/7;j++){
            Contactperson contactperson1= new Contactperson();
            System.out.println("j================== "+j);
            String data = new String();
            for (int i=0+j*7;i<j*7+7;i++){
                data = cotactPersonL.get(i);
                System.out.println(i+"      "+ cotactPersonL.get(i));
                if(i%7==0){
                    contactperson1.setWechat(data);
                    contactperson1.setName(data);
                }else if (i%7 == 1){
                    contactperson1.setPhonenumber(data);
                    contactperson1.setGender(data);
                }else if(i%7 ==2){
                    contactperson1.setIdentity(data);
                    contactperson1.setPhonenumber(data);
                }else if (i%7==3){
                    contactperson1.setHomephonenumber(data);
                }else if (i%7==4){
                    contactperson1.setWechat(data);
                }else if (i%7==5){
                    contactperson1.setEmail(data);
                }else if (i%7==6){
                    contactperson1.setIdentity(data);
                }
            }
            contactpersonService.insert2(contactperson1);
        }


        //批量添加联系地址
        contactaddress = URLDecoder.decode(contactaddress, charset);
        Map<String, String> mapContactaddress=new LinkedHashMap<String, String>();
        System.out.println(contactaddress);
        String[] arr2=contactaddress.split("&");
        for (int i = 0; i <arr2.length; i++) {
            String key=arr2[i].substring(0, arr2[i].indexOf("="));
            String value=arr2[i].substring( arr2[i].indexOf("=")+1);
            mapContactaddress.put(key, value);
        }
        System.out.println(mapContactaddress);
        System.out.println(mapContactaddress.size());
        List<String> cotactAddressL = new ArrayList<String>();
        for(Object key : mapContactaddress.keySet()){
            String value = mapContactaddress.get(key);
            cotactAddressL.add(value);
            System.out.println(value);
        }
        System.out.println("cotactAddressL............");
        for(int i=0;i<cotactAddressL.size();i++){
            System.out.println(cotactAddressL.get(i));
        }
        System.out.println(cotactAddressL.size()/6);
        System.out.println((cotactAddressL.size())/6);
        for(int j=0;j<(cotactAddressL.size())/6;j++){
            Contactaddress contactaddress1 = new Contactaddress();
            System.out.println("j================== "+j);
            String data = new String();
            for (int i=0+j*6;i<j*6+6;i++){
                data = cotactAddressL.get(i);
                System.out.println(i+"      "+ cotactAddressL.get(i));
                if(i%6==0){
                    contactaddress1.setTitle(data);
                }else if (i%6 == 1){
                    contactaddress1.setStampnumber(Integer.parseInt(data));
                }else if(i%6 ==2){
                    contactaddress1.setCountry(data);
                }else if (i%6==3){
                    contactaddress1.setProvince(data);
                }else if (i%6==4){
                    contactaddress1.setCity(data);
                }else if (i%6==5){
                    contactaddress1.setDistrict(data);
                }
            }
            contactaddressService.insert2(contactaddress1);
        }

        return mapContactaddress;

    }

    @DeleteMapping("deleteAllById") //通过guid删除客户信息、联系人、联系地址
    public String deleteAllById(Integer id){
//        return this.contactpersonService.deleteById(id);
        System.out.println("...............");
        System.out.println(id);
        contactpersonService.deleteByCid(id);
        contactaddressService.deleteByCid(id);
        customerService.deleteById(id);
        return "this.contactpersonService.deleteById(id)";
    }
//    @PostMapping(value = "addCustomer",produces="application/json")
//    public String addCustomer(@RequestBody Customer customer){
//        return customer.getUsername();
//    }

//

//    @PostMapping(value = "addCustomer")
//    public String addCustomer(HttpServletRequest req){
//        String s = (String) req.getAttribute("username");
//        return s;
//    }


//
//    @GetMapping("addCustomerInfo")
//    public String addCustomerInfo(@RequestParam("customerUsername") String customerUsername, @RequestParam("contactpersonName") String contactpersonName,
//                                  @RequestParam("contactpersonGender") String contactpersonGender, @RequestParam("contactpersonPhonenumber") String contactpersonPhonenumber,
//                                  @RequestParam("contactpersonHomephonenumber") String contactpersonHomephonenumber, @RequestParam("contactpersonWechat") String contactpersonWechat,
//                                  @RequestParam("contactpersonEmail") String contactpersonEmail, @RequestParam("contactpersonIdentity") String contactpersonIdentity,
//                                  @RequestParam("contactaddressTitle") String contactaddressTitle, @RequestParam("contactaddressStampnumber") Integer contactaddressStampnumber,
//                                  @RequestParam("contactaddressCountry") String contactaddressCountry, @RequestParam("contactaddressProvince") String contactaddressProvince,
//                                  @RequestParam("contactaddressCity") String contactaddressCity, @RequestParam("contactaddressDistrict") String contactaddressDistrict
//                                  ){
//        Customer customer = new Customer();
//        customer.setUsername(customerUsername);
//        customerService.insert(customer);
//        Contactperson contactperson = new Contactperson();
//        contactperson.setName(contactpersonName);
//        contactperson.setGender(contactpersonGender);
//        contactperson.setPhonenumber(contactpersonPhonenumber);
//        contactperson.setWechat(contactpersonWechat);
//        contactperson.setHomephonenumber(contactpersonHomephonenumber);
//        contactperson.setEmail(contactpersonEmail);
//        contactperson.setIdentity(contactpersonIdentity);
//        contactpersonService.insertCustomer(contactperson);
//        Contactaddress contactaddress = new Contactaddress();
//        contactaddress.setTitle(contactaddressTitle);
//        contactaddress.setStampnumber(contactaddressStampnumber);
//        contactaddress.setCountry(contactaddressCountry);
//        contactaddress.setProvince(contactaddressProvince);
//        contactaddress.setCity(contactaddressCity);
//        contactaddress.setDistrict(contactaddressDistrict);
//        contactaddressService.insertCustomer(contactaddress);
//        return "新增客户成功！";
//    }

}
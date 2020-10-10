package com.example.demo.controller;

import com.example.demo.dao.ContactpersonDao;
import com.example.demo.entity.Contactaddress;
import com.example.demo.entity.Contactperson;
import com.example.demo.entity.Customer;
import com.example.demo.service.ContactaddressService;
import com.example.demo.service.ContactpersonService;
import com.example.demo.service.CustomerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Customer selectOne(Integer id) {
        return this.customerService.queryById(id);
    }

    @GetMapping("selectAll")
    public List<Customer> selectAll() {
        return this.customerService.selectAll();
    }

//    返回客户信息并且含有客户联系人和联系地址
    @GetMapping("selectAllTest")
    public List selectAllTest() {
        List<Customer> customers = this.customerService.selectAll();
        List<Map<String, Object>> Lists = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < customers.size(); i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("contactaddress",contactaddressService.selectByCid(customers.get(i).getGuid()));
            map.put("guid", customers.get(i).getGuid());
            map.put("username",customers.get(i).getUsername());
            map.put("contactperson", contactpersonService.selectByCid(customers.get(i).getGuid()));
            Lists.add(map);
        }
        return Lists;
    }

    @GetMapping("addCustomerInfo")
    public String addCustomerInfo(@RequestParam("customerUsername") String customerUsername, @RequestParam("contactpersonName") String contactpersonName,
                                  @RequestParam("contactpersonGender") String contactpersonGender, @RequestParam("contactpersonPhonenumber") String contactpersonPhonenumber,
                                  @RequestParam("contactpersonHomephonenumber") String contactpersonHomephonenumber, @RequestParam("contactpersonWechat") String contactpersonWechat,
                                  @RequestParam("contactpersonEmail") String contactpersonEmail, @RequestParam("contactpersonIdentity") String contactpersonIdentity,
                                  @RequestParam("contactaddressTitle") String contactaddressTitle, @RequestParam("contactaddressStampnumber") Integer contactaddressStampnumber,
                                  @RequestParam("contactaddressCountry") String contactaddressCountry, @RequestParam("contactaddressProvince") String contactaddressProvince,
                                  @RequestParam("contactaddressCity") String contactaddressCity, @RequestParam("contactaddressDistrict") String contactaddressDistrict
                                  ){
        Customer customer = new Customer();
        customer.setUsername(customerUsername);
        customerService.insert(customer);
        Contactperson contactperson = new Contactperson();
        contactperson.setName(contactpersonName);
        contactperson.setGender(contactpersonGender);
        contactperson.setPhonenumber(contactpersonPhonenumber);
        contactperson.setWechat(contactpersonWechat);
        contactperson.setHomephonenumber(contactpersonHomephonenumber);
        contactperson.setEmail(contactpersonEmail);
        contactperson.setIdentity(contactpersonIdentity);
        contactpersonService.insertCustomer(contactperson);
        Contactaddress contactaddress = new Contactaddress();
        contactaddress.setTitle(contactaddressTitle);
        contactaddress.setStampnumber(contactaddressStampnumber);
        contactaddress.setCountry(contactaddressCountry);
        contactaddress.setProvince(contactaddressProvince);
        contactaddress.setCity(contactaddressCity);
        contactaddress.setDistrict(contactaddressDistrict);
        contactaddressService.insertCustomer(contactaddress);
        return "新增客户成功！";
    }

}
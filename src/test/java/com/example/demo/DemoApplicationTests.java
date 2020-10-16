package com.example.demo;

import com.example.demo.dao.ContactaddressDao;
import com.example.demo.dao.ContactpersonDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.entity.Contactaddress;
import com.example.demo.entity.Contactperson;
import com.example.demo.entity.Customer;
import com.example.demo.service.ContactaddressService;
import com.example.demo.service.ContactpersonService;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ContactpersonService contactpersonService;
    @Autowired
    private ContactpersonDao contactpersonDao;

    @Autowired
    private ContactaddressService contactaddressService;
    @Autowired
    private ContactaddressDao contactaddressDao;

    @Test
    void contextLoads() {
    }
    //增加数据
//    @Test
//    void test1(){
//        System.out.println("增加数据");
//        Customer customer = new Customer(guid, username, notes);
//        customer.setGuid(7);
//        customer.setUsername("陈伟fvsdfsdafvsd");
//        customerService.insert(customer);
//    }
    //删除数据
    @Test
    void test2(){
        System.out.println("删除数据");
        customerService.deleteById(7);
    }
    //修改数据
//    @Test
//    void test3(){
//        System.out.println("修改数据");
//        Customer customer = new Customer(guid, username, notes);
//        customer.setGuid(3);
//        customer.setUsername("asdgasdgfsdafgasd");
//        customerService.update(customer);
//    }
    //查询数据
    @Test
    void test4(){
        System.out.println("查询数据");
        Customer customer = customerService.queryById(3);
        System.out.println(customer.getGuid());
        System.out.println(customer.getUsername());
    }

    @Test
    void test5(){
        System.out.println("查询selectALL数据");
        List<Customer> a = new ArrayList<>();
        a = customerDao.selectAll();
        System.out.println(a.get(0).getUsername());
    }
    @Test
    void test6(){
        System.out.println("查询contactperson selectALL数据");
        List<Contactperson> a = new ArrayList<>();
        a = contactpersonDao.selectAll();
        System.out.println(a.get(0).getName());
    }
    @Test
    void test8(){
        System.out.println("添加客户时，添加联系人");
        Contactperson contactperson = new Contactperson();
        contactperson.setEmail("test");
        contactperson.setGender("test");
        contactperson.setHomephonenumber("444");
        contactperson.setIdentity("test");
        contactperson.setPhonenumber("5");
        contactperson.setName("test");
        contactperson.setWechat("test");
        contactpersonDao.insertCustomer(contactperson);
    }
    @Resource
    private CustomerService customerService2;
    @Test
    void test9(){
        System.out.println("查询所有客户，并带");
        List<Customer> list = this.customerService2.selectAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    void test10(){
        System.out.println("模糊查询");
        List<Customer> list = this.customerService.fuzzyQueryByName("a");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list.get(i).getUsername() + \" \" + list.get(i).getNotes() + \" \" + list.get(i).getGuid()");
            System.out.println(list.get(i).getUsername() + " " + list.get(i).getNotes() + " " + list.get(i).getGuid());
        }
    }
    @Test
    void test11(){
        System.out.println("insert");
        Customer customer = new Customer();
        customer.setNotes("bbbbbb");
        customer.setUsername("bbbbbb");
        customer.setRole(1);
        customerDao.insert(customer);
        System.out.println(contactaddressDao.selectByCid(84));
    }    @Test
    void test12(){
        System.out.println("updateAddressItem");
        Contactaddress contactaddress = new Contactaddress();
        contactaddress.setCountry("中国");
        contactaddress.setId(131);
        contactaddress.setProvince("中国");
        contactaddress.setDetaileara("detaileara");
        contactaddressDao.update(contactaddress);
    }

}

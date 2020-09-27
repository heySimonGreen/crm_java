package com.example.demo;

import com.example.demo.dao.ContactpersonDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.entity.Contactperson;
import com.example.demo.entity.Customer;
import com.example.demo.service.ContactpersonService;
import com.example.demo.service.CustomerService;
import org.apache.catalina.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jnlp.ClipboardService;
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

    @Test
    void contextLoads() {
    }
    //增加数据
    @Test
    void test1(){
        System.out.println("增加数据");
        Customer customer = new Customer();
        customer.setGuid(7);
        customer.setUsername("陈伟fvsdfsdafvsd");
        customerService.insert(customer);
    }
    //删除数据
    @Test
    void test2(){
        System.out.println("删除数据");
        customerService.deleteById(7);
    }
    //修改数据
    @Test
    void test3(){
        System.out.println("修改数据");
        Customer customer = new Customer();
        customer.setGuid(3);
        customer.setUsername("asdgasdgfsdafgasd");
        customerService.update(customer);
    }
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

}

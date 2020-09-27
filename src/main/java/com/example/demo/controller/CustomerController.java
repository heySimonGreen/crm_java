package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        return this.customerService.selectaAll();
    }

}
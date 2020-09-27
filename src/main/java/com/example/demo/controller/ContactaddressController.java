package com.example.demo.controller;

import com.example.demo.entity.Contactaddress;
import com.example.demo.service.ContactaddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Contactaddress)表控制层
 *
 * @author chenwei
 * @since 2020-09-22 23:41:56
 */
@RestController
@RequestMapping("contactaddress")
@ResponseBody
@CrossOrigin
public class ContactaddressController {
    /**
     * 服务对象
     */
    @Resource
    private ContactaddressService contactaddressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Contactaddress selectOne(Integer id) {
        return this.contactaddressService.queryById(id);
    }

    @GetMapping("selectAll")
    public List<Contactaddress> selectAll() {
        return this.contactaddressService.selectAll();
    }

}
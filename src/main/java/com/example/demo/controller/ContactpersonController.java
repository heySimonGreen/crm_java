package com.example.demo.controller;

import com.example.demo.entity.Contactperson;
import com.example.demo.service.ContactpersonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Contactperson)表控制层
 *
 * @author chenwei
 * @since 2020-09-22 23:41:57
 */
@RestController
@RequestMapping("contactperson")
@ResponseBody
@CrossOrigin
public class ContactpersonController {
    /**
     * 服务对象
     */
    @Resource
    private ContactpersonService contactpersonService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Contactperson selectOne(Integer id) {
        return this.contactpersonService.queryById(id);
    }

    @GetMapping("selectAll")
    public List<Contactperson> selectAll() {
        return this.contactpersonService.selectAll();
    }

    @GetMapping("selectByCid")
    public List<Contactperson> selectByCid(Integer cid) {
        return this.contactpersonService.selectByCid(cid);
    }

}
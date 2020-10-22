package com.example.demo.service.impl;

import com.example.demo.dao.ContactpersonDao;
import com.example.demo.entity.Contactperson;
import com.example.demo.service.ContactpersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Contactperson)表服务实现类
 *
 * @author chenwei
 * @since 2020-09-22 23:41:57
 */
@Service("contactpersonService")
public class ContactpersonServiceImpl implements ContactpersonService {
    @Resource
//    @Autowired
    private ContactpersonDao contactpersonDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Contactperson queryById(Integer id) {
        return this.contactpersonDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Contactperson> queryAllByLimit(int offset, int limit) {
        return this.contactpersonDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param contactperson 实例对象
     * @return 实例对象
     */
    @Override
    public Contactperson insert(Contactperson contactperson) {
        this.contactpersonDao.insert(contactperson);
        return contactperson;
    }
    @Override
    public Contactperson insert2(Contactperson contactperson) {
        this.contactpersonDao.insert2(contactperson);
        return contactperson;
    }

    /**
     * 修改数据
     *
     * @param contactperson 实例对象
     * @return 实例对象
     */
    @Override
    public Contactperson update(Contactperson contactperson) {
        this.contactpersonDao.update(contactperson);
        return this.queryById(contactperson.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.contactpersonDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByCid(String cid) {
        return this.contactpersonDao.deleteByCid(cid) > 0;
    }

    @Override
    public List<Contactperson> selectAll() {
        return contactpersonDao.selectAll();
    }

    @Override
    public List<Contactperson> selectByCid(String cid) {
        return contactpersonDao.selectByCid(cid);
    }

    @Override
    public Contactperson insertCustomer(Contactperson contactperson) {
        contactpersonDao.insertCustomer(contactperson);
        return contactperson;
    }
}
package com.example.demo.service.impl;

import com.example.demo.dao.ContactaddressDao;
import com.example.demo.entity.Contactaddress;
import com.example.demo.service.ContactaddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Contactaddress)表服务实现类
 *
 * @author chenwei
 * @since 2020-09-22 23:41:55
 */
@Service("contactaddressService")
public class ContactaddressServiceImpl implements ContactaddressService {
    @Resource
//    @Autowired
    private ContactaddressDao contactaddressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Contactaddress queryById(Integer id) {
        return this.contactaddressDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Contactaddress> queryAllByLimit(int offset, int limit) {
        return this.contactaddressDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param contactaddress 实例对象
     * @return 实例对象
     */
    @Override
    public Contactaddress insert(Contactaddress contactaddress) {
        this.contactaddressDao.insert(contactaddress);
        return contactaddress;
    }

    /**
     * 修改数据
     *
     * @param contactaddress 实例对象
     * @return 实例对象
     */
    @Override
    public Contactaddress update(Contactaddress contactaddress) {
        this.contactaddressDao.update(contactaddress);
        return this.queryById(contactaddress.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.contactaddressDao.deleteById(id) > 0;
    }

    @Override
    public List<Contactaddress> selectAll() {
        return contactaddressDao.selectAll();
    }

    @Override
    public Contactaddress insertCustomer(Contactaddress contactaddress) {
        contactaddressDao.insertCustomer(contactaddress);
        return contactaddress;
    }

    @Override
    public List<Contactaddress> selectByCid(Integer Cid) {
        return this.contactaddressDao.selectByCid(Cid);
    }
}
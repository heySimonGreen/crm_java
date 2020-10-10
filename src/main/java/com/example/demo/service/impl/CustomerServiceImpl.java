package com.example.demo.service.impl;

import com.example.demo.dao.CustomerDao;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Customer)表服务实现类
 *
 * @author chenwei
 * @since 2020-09-22 23:41:58
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource
//    @Autowired
    private CustomerDao customerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param guid 主键
     * @return 实例对象
     */
    @Override
    public Customer queryById(Integer guid) {
        return this.customerDao.queryById(guid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Customer> queryAllByLimit(int offset, int limit) {
        return this.customerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer insert(Customer customer) {
        this.customerDao.insert(customer);
        return customer;
    }

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer update(Customer customer) {
        this.customerDao.update(customer);
        return this.queryById(customer.getGuid());
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer guid) {
        return this.customerDao.deleteById(guid) > 0;
    }

    @Override
    public List<Customer> selectAll() {
        return this.customerDao.selectAll();
    }
}
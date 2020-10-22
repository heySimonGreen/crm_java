package com.example.demo.service;

import com.example.demo.entity.Customer;

import java.util.List;

/**
 * (Customer)表服务接口
 *
 * @author chenwei
 * @since 2020-09-22 23:41:58
 */
public interface CustomerService {

    /**
     * 通过ID查询单条数据
     *
     * @param guid 主键
     * @return 实例对象
     */
    Customer queryById(String guid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Customer> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer insert(Customer customer);

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    boolean deleteById(String guid);

    List<Customer> selectAll();

    //模糊查询，通过usernamename查询所有username为他的数据
    List<Customer> fuzzyQueryByName(String username, Integer role);

    List<Customer> queryAll(Customer customer);


}
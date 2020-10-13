package com.example.demo.service;

import com.example.demo.entity.Contactaddress;

import java.util.List;

/**
 * (Contactaddress)表服务接口
 *
 * @author chenwei
 * @since 2020-09-22 23:41:55
 */
public interface ContactaddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Contactaddress queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Contactaddress> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param contactaddress 实例对象
     * @return 实例对象
     */
    Contactaddress insert(Contactaddress contactaddress);
    Contactaddress insert2(Contactaddress contactaddress);

    /**
     * 修改数据
     *
     * @param contactaddress 实例对象
     * @return 实例对象
     */
    Contactaddress update(Contactaddress contactaddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    //通过cid删除整行信息
    boolean deleteByCid(Integer cid);

    List<Contactaddress> selectAll();

//    Contactaddress insertCustomer(Contactaddress contactaddress);

    List<Contactaddress> selectByCid(Integer Cid);

}
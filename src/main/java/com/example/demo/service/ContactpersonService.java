package com.example.demo.service;

import com.example.demo.entity.Contactperson;

import java.util.List;

/**
 * (Contactperson)表服务接口
 *
 * @author chenwei
 * @since 2020-09-22 23:41:57
 */
public interface ContactpersonService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Contactperson queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Contactperson> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param contactperson 实例对象
     * @return 实例对象
     */
    Contactperson insert(Contactperson contactperson);

    /**
     * 修改数据
     *
     * @param contactperson 实例对象
     * @return 实例对象
     */
    Contactperson update(Contactperson contactperson);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Contactperson> selectAll();

    List<Contactperson> selectByCid(Integer cid);

    Contactperson insertCustomer(Contactperson contactperson);

}
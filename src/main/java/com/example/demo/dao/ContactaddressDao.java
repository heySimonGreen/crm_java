package com.example.demo.dao;

import com.example.demo.entity.Contactaddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Contactaddress)表数据库访问层
 *
 * @author chenwei
 * @since 2020-09-22 23:41:54
 */
public interface ContactaddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Contactaddress queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Contactaddress> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param contactaddress 实例对象
     * @return 对象列表
     */
    List<Contactaddress> queryAll(Contactaddress contactaddress);

    /**
     * 新增数据
     *
     * @param contactaddress 实例对象
     * @return 影响行数
     */
    int insert(Contactaddress contactaddress);

    /**
     * 修改数据
     *
     * @param contactaddress 实例对象
     * @return 影响行数
     */
    int update(Contactaddress contactaddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Contactaddress> selectAll();

}
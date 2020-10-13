package com.example.demo.dao;

import com.example.demo.entity.Contactperson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Contactperson)表数据库访问层
 *
 * @author chenwei
 * @since 2020-09-22 23:41:57
 */
public interface ContactpersonDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Contactperson queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Contactperson> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param contactperson 实例对象
     * @return 对象列表
     */
    List<Contactperson> queryAll(Contactperson contactperson);

    /**
     * 新增数据
     *
     * @param contactperson 实例对象
     * @return 影响行数
     */
    int insert(Contactperson contactperson);
    int insert2(Contactperson contactperson);

    /**
     * 修改数据
     *
     * @param contactperson 实例对象
     * @return 影响行数
     */
    int update(Contactperson contactperson);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Contactperson> selectAll();

    List<Contactperson> selectByCid(Integer cid);

    int insertCustomer(Contactperson contactperson);

    //通过cid删除整行信息
    int deleteByCid(Integer cid);

}
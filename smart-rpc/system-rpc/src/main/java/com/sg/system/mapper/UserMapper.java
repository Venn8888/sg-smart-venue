package com.sg.system.mapper;

import com.sg.common.exception.SgException;
import com.sg.system.entity.User;
import java.util.List;

/**
 * 用户Mapper
 * @author venn
 * 2020-07-29
 */
public interface UserMapper {

    /**
    * 获取用户所有信息
    * @param user
    */
    List<User> selectListAll(User user);

    /**
    * 通过id获取信息
    * @param id
    */
    List<User> selectById(int id);

    /**
     * 用户 新增数据
     * @param user
    */
    void insert(User user) throws SgException;

    /**
     * 用户 更新数据
     * @param user
     */
    void updateById(User user) throws SgException;


    /**
     * 用户 通过id删除数据
     * @param id
     */
    void deleteById(int id) throws SgException;

    /**
     * 用户 通过id批量删除数据
     * @param ids
     */
    void deleteByIds(String ids) throws SgException;

    /**
     * 用户 批量新增数据
     * @param list
     */
    void saveForeach(List<User> list) throws SgException;

}
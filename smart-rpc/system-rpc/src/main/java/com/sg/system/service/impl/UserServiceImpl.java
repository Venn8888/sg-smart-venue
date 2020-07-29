package com.sg.system.service.impl;

import com.sg.common.exception.SgException;
import com.sg.system.entity.User;
import com.sg.system.mapper.UserMapper;
import com.sg.system.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-29
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectListAll(User user){
        return userMapper.selectListAll(user);
    }

    @Override
    public List<User> selectById(int id){
        return userMapper.selectById(id);
    }

    @Override
    public void insert(User user) throws SgException {
        userMapper.insert(user);
    }

    @Override
    public void updateById(User user) throws SgException{
        userMapper.updateById(user);
    }


    @Override
    public void deleteById(int id) throws SgException{
        userMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(String ids) throws SgException{
        userMapper.deleteByIds(ids);
    }

    @Override
    public void saveForeach(List<User> list) throws SgException{
        userMapper.saveForeach(list);
    }
}

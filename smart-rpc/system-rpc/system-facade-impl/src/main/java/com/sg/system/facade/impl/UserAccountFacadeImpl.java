package com.sg.system.facade.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.system.domain.UserAccountDomain;
import com.sg.system.facade.UserAccountFacade;
import com.sg.system.mapper.UserAccountMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <p>
 * 用户帐号 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
@Component
public class UserAccountFacadeImpl extends ServiceImpl<UserAccountMapper, UserAccountDomain> implements UserAccountFacade {

    private UserAccountMapper userAccountMapper;

    public UserAccountFacadeImpl(UserAccountMapper userAccountMapper) {
        this.userAccountMapper = userAccountMapper;
    }

    @Override
    public Set<String> authsbyaccountid(String accountId) {
        return userAccountMapper.authSByAccountId(accountId);
    }

    @Override
    public UserAccountDomain getByUserName(String userName) {
        return userAccountMapper.getByUserName(userName);
    }
}

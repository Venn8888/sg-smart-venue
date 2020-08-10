package com.sg.system.facade;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.system.domain.UserAccountDomain;

import java.util.Set;

/**
 * <p>
 * 用户帐号 服务类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
public interface UserAccountFacade extends IService<UserAccountDomain> {

    /**
     * 根据用户id 获取 用户权限
     * @param accountId
     * @return
     */
    Set<String> authsbyaccountid(String accountId);

    /**
     *
     */
    UserAccountDomain getByUserName(String userName);
}

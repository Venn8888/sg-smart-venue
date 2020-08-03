package com.sg.system.facade;

import com.sg.system.domain.UserAccountDomain;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
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
    Set<String> authSByAccountId(String accountId);
}

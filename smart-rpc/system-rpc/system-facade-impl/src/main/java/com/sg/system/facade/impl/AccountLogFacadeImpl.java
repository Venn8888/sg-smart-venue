package com.sg.system.facade.impl;

import com.sg.system.domain.AccountLogDomain;
import com.sg.system.mapper.AccountLogMapper;
import com.sg.system.facade.AccountLogFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ???????—￥??—è?¨ 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class AccountLogFacadeImpl extends ServiceImpl<AccountLogMapper, AccountLogDomain> implements AccountLogFacade {

}

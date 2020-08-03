package com.sg.system.facade.impl;

import com.sg.system.domain.AuthorityDomain;
import com.sg.system.mapper.AuthorityMapper;
import com.sg.system.facade.AuthorityFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务资源 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class AuthorityFacadeImpl extends ServiceImpl<AuthorityMapper, AuthorityDomain> implements AuthorityFacade {

}

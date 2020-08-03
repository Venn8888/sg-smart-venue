package com.sg.system.facade.impl;

import com.sg.system.domain.RoleAuthorityDomain;
import com.sg.system.mapper.RoleAuthorityMapper;
import com.sg.system.facade.RoleAuthorityFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class RoleAuthorityFacadeImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthorityDomain> implements RoleAuthorityFacade {

}

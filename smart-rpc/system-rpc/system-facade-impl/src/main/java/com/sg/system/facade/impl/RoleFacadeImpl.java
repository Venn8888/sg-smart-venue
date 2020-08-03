package com.sg.system.facade.impl;

import com.sg.system.domain.RoleDomain;
import com.sg.system.mapper.RoleMapper;
import com.sg.system.facade.RoleFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class RoleFacadeImpl extends ServiceImpl<RoleMapper, RoleDomain> implements RoleFacade {

}

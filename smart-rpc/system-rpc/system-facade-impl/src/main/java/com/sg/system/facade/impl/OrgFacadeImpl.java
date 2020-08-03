package com.sg.system.facade.impl;

import com.sg.system.domain.OrgDomain;
import com.sg.system.mapper.OrgMapper;
import com.sg.system.facade.OrgFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class OrgFacadeImpl extends ServiceImpl<OrgMapper, OrgDomain> implements OrgFacade {

}

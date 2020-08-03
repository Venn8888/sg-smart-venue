package com.sg.system.facade.impl;

import com.sg.system.domain.DataDomainDomain;
import com.sg.system.mapper.DataDomainMapper;
import com.sg.system.facade.DataDomainFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限对象 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class DataDomainFacadeImpl extends ServiceImpl<DataDomainMapper, DataDomainDomain> implements DataDomainFacade {

}

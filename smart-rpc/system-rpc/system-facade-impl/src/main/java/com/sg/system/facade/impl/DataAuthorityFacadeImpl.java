package com.sg.system.facade.impl;

import com.sg.system.domain.DataAuthorityDomain;
import com.sg.system.mapper.DataAuthorityMapper;
import com.sg.system.facade.DataAuthorityFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据权限 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class DataAuthorityFacadeImpl extends ServiceImpl<DataAuthorityMapper, DataAuthorityDomain> implements DataAuthorityFacade {

}

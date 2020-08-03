package com.sg.system.facade.impl;

import com.sg.system.domain.DimensionDomain;
import com.sg.system.mapper.DimensionMapper;
import com.sg.system.facade.DimensionFacade;
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
public class DimensionFacadeImpl extends ServiceImpl<DimensionMapper, DimensionDomain> implements DimensionFacade {

}

package com.sg.system.facade.impl;

import com.sg.system.domain.DataDimensionDomain;
import com.sg.system.mapper.DataDimensionMapper;
import com.sg.system.facade.DataDimensionFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限设置 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class DataDimensionFacadeImpl extends ServiceImpl<DataDimensionMapper, DataDimensionDomain> implements DataDimensionFacade {

}

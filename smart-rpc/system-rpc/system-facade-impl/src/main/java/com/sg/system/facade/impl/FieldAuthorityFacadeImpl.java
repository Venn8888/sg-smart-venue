package com.sg.system.facade.impl;

import com.sg.system.domain.FieldAuthorityDomain;
import com.sg.system.mapper.FieldAuthorityMapper;
import com.sg.system.facade.FieldAuthorityFacade;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字段权限 服务实现类
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
@Service
public class FieldAuthorityFacadeImpl extends ServiceImpl<FieldAuthorityMapper, FieldAuthorityDomain> implements FieldAuthorityFacade {

}

package com.sg.system.mapper;

import com.sg.system.domain.UserAccountDomain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户帐号 Mapper 接口
 * </p>
 *
 * @author venn
 * @since 2020-07-30
 */
public interface UserAccountMapper extends BaseMapper<UserAccountDomain> {

    Set<String> authSByAccountId(String accountId);

}

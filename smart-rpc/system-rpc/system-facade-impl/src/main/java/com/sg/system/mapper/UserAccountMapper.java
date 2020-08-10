package com.sg.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.system.domain.UserAccountDomain;
import org.apache.ibatis.annotations.Param;

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

    UserAccountDomain getByUserName(@Param("userName") String userName);
}

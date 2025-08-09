package com.prodigal.aicode.mapper;

import com.mybatisflex.core.BaseMapper;
import com.prodigal.aicode.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户 映射层。
 *
 * @author Lang
 * @since 2025-08-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

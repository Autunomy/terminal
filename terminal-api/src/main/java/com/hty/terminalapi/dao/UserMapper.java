package com.hty.terminalapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hty.terminalapi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

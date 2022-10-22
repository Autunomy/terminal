package com.hty.terminalapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hty.terminalapi.pojo.Connection;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConnectionMapper extends BaseMapper<Connection> {
}

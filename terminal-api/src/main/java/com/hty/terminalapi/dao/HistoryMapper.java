package com.hty.terminalapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hty.terminalapi.pojo.History;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
}

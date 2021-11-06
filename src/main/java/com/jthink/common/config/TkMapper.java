package com.jthink.common.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {

}

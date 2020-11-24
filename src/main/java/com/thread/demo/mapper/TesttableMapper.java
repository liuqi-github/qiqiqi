package com.thread.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.mapstruct.Mapper;
import com.thread.demo.entity.Testtable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author mpcoder
 * @since 2020-08-28
 */
@Mapper
public interface TesttableMapper extends BaseMapper<Testtable> {
    public Testtable selectAllNum();
}

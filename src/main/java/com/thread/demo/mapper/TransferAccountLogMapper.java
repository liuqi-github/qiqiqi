package com.thread.demo.mapper;

import com.thread.demo.entity.TransferAccountLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-27
 */
public interface TransferAccountLogMapper extends BaseMapper<TransferAccountLog> {
    LocalDateTime findLastTime();
}

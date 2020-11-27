package com.thread.demo.mapper;

import com.thread.demo.entity.TransferOrderTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-26
 */
@Mapper
public interface TransferOrderTableMapper extends BaseMapper<TransferOrderTable> {
    List<TransferOrderTable> findMsgIdByTime(LocalDateTime startTime);

    LocalDateTime findMaxTime();
}

package com.thread.demo.mapper;

import com.thread.demo.entity.MessageTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-25
 */
@Mapper
public interface MessageTableMapper extends BaseMapper<MessageTable> {
    List<Integer> findReSendMessageId();
}

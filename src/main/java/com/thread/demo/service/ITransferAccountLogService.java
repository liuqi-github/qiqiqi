package com.thread.demo.service;

import com.thread.demo.entity.TransferAccountLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-27
 */
public interface ITransferAccountLogService extends IService<TransferAccountLog> {
        LocalDateTime findLastTime();
}

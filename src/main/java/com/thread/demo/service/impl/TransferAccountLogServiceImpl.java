package com.thread.demo.service.impl;

import com.thread.demo.entity.TransferAccountLog;
import com.thread.demo.mapper.TransferAccountLogMapper;
import com.thread.demo.service.ITransferAccountLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-27
 */
@Service
public class TransferAccountLogServiceImpl extends ServiceImpl<TransferAccountLogMapper, TransferAccountLog> implements ITransferAccountLogService {
    private TransferAccountLogMapper transferAccountLogMapper;

    @Autowired
    public TransferAccountLogServiceImpl(TransferAccountLogMapper transferAccountLogMapper) {
        this.transferAccountLogMapper = transferAccountLogMapper;
    }


    @Override
    public LocalDateTime findLastTime() {
        return transferAccountLogMapper.findLastTime();
    }
}

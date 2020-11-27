package com.thread.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thread.demo.entity.Account;
import com.thread.demo.entity.TransferOrderTable;
import com.thread.demo.mapper.AccountMapper;
import com.thread.demo.mapper.TransferOrderTableMapper;
import com.thread.demo.service.IMessageTableService;
import com.thread.demo.service.ITransferOrderTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-26
 */
@Service
public class TransferOrderTableServiceImpl extends ServiceImpl<TransferOrderTableMapper, TransferOrderTable> implements ITransferOrderTableService {
    private final  TransferOrderTableMapper transferOrderTableMapper;

    @Autowired
    public TransferOrderTableServiceImpl(TransferOrderTableMapper transferOrderTableMapper){
            this.transferOrderTableMapper = transferOrderTableMapper;
    }



    @DS("db1")
    public boolean saveDb1(TransferOrderTable entity) {
        return super.save(entity);
    }

    public boolean saveMaster(TransferOrderTable entity) {
        return super.save(entity);
    }

    @DS("db1")
    public TransferOrderTable getOneDb1(Wrapper<TransferOrderTable> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    @DS("master")
    public TransferOrderTable getOneMaster(Wrapper<TransferOrderTable> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    public boolean updateMaster(Wrapper<TransferOrderTable> updateWrapper) {
        return super.update(updateWrapper);
    }

    @DS("db1")
    public boolean updateDb1(Wrapper<TransferOrderTable> updateWrapper) {
        return super.update(updateWrapper);
    }

    @DS("master")
    public List<TransferOrderTable> findMsgIdByTimeMaster(LocalDateTime startTime){
        return   transferOrderTableMapper.findMsgIdByTime(startTime);
    }

    @DS("db1")
    public List<TransferOrderTable> findMsgIdByTimeDb1(LocalDateTime startTime){
        return   transferOrderTableMapper.findMsgIdByTime(startTime);
    }

    @DS("db1")
    public LocalDateTime findMaxTimeDb1(){
        return  transferOrderTableMapper.findMaxTime();
    }

    @DS("master")
    public LocalDateTime findMaxTimeMaster(){
        return  transferOrderTableMapper.findMaxTime();
    }
}

package com.thread.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.thread.demo.entity.MessageTable;
import com.thread.demo.mapper.MessageTableMapper;
import com.thread.demo.service.IMessageTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-25
 */
@Service
@DS("master")
public class MessageTableServiceImpl extends ServiceImpl<MessageTableMapper, MessageTable> implements IMessageTableService {
    private final MessageTableMapper messageTableMapper;

    @Autowired
    public MessageTableServiceImpl(MessageTableMapper messageTableMapper){
        this.messageTableMapper = messageTableMapper;
    }


    @Override
    public int insertMessage(String msg) {
        MessageTable messageTable = new MessageTable();
        messageTable.setMessage(msg);
        messageTable.setStatus(0);
        messageTableMapper.insert(messageTable);
        return messageTable.getId();
    }
}

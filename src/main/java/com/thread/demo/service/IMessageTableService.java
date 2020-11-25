package com.thread.demo.service;

import com.thread.demo.entity.MessageTable;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-25
 */
public interface IMessageTableService extends IService<MessageTable> {
    public int insertMessage(String msg);
}

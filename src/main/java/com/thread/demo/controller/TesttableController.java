package com.thread.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thread.demo.enums.MessageTypeEnum;
import com.thread.demo.service.Producer;
import com.thread.demo.service.TransferAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.thread.demo.entity.Testtable;
import com.thread.demo.service.ITesttableService;
import com.thread.demo.service.impl.TesttableServiceImpl;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mpcoder
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/testtable/")
public class TesttableController {
    private final TesttableServiceImpl testtableService;
    private final TransferAccountService transferAccountService;
    private final Producer producer;


    @Autowired
    public TesttableController(TesttableServiceImpl testtableService,
                               Producer producer,
                               TransferAccountService transferAccountService) {
        this.testtableService = testtableService;
        this.producer = producer;
        this.transferAccountService = transferAccountService;
    }

    @PostMapping("getAll")
    public IPage teachYouNumber(Integer current, Integer size) {
        IPage<Testtable> page = new Page<>(current, size);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id", 1);
        wrapper.orderByDesc("id");
        IPage result = testtableService.page(page, wrapper);
        return result;
    }

    @PostMapping("deleteOne")
    public Integer deleteOne(Integer id) {
        return testtableService.getBaseMapper().deleteById(id);
    }

    @PostMapping("allNumber")
    public Testtable getAllNumber() {
        return testtableService.getAllNum();
    }

    @PostMapping("transferAccount")
    public void transferAccount(MessageTypeEnum messageTypeEnum, String accountName, String toAccount, Integer money) {
        transferAccountService.generateOrder(messageTypeEnum, accountName, toAccount, money);
    }

}

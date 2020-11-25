package com.thread.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import com.thread.demo.entity.Testtable;
import com.thread.demo.mapper.TesttableMapper;
import com.thread.demo.service.ITesttableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mpcoder
 * @since 2020-08-28
 */
@Service
@DS("master")
public class TesttableServiceImpl extends ServiceImpl<TesttableMapper, Testtable> implements ITesttableService {
    private final TesttableMapper testtableMapper;

    //如果只有一个构造器可以省略autowirde注解
    @Autowired
    public TesttableServiceImpl(TesttableMapper testtableMapper) {
        this.testtableMapper = testtableMapper;
    }

    @Override
    public Testtable getAllNum() {
        return testtableMapper.selectAllNum();
    }
}



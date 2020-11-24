package com.thread.demo.service;

import com.thread.demo.entity.Testtable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mpcoder
 * @since 2020-08-28
 */
public interface ITesttableService extends IService<Testtable> {
    public Testtable getAllNum();
}

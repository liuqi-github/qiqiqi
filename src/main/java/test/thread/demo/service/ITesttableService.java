package test.thread.demo.service;

import org.springframework.stereotype.Service;
import test.thread.demo.entity.Testtable;
import com.baomidou.mybatisplus.extension.service.IService;
import test.thread.demo.mapper.TesttableMapper;

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

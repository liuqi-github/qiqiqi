package test.thread.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.thread.demo.entity.Testtable;
import test.thread.demo.mapper.TesttableMapper;
import test.thread.demo.service.ITesttableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mpcoder
 * @since 2020-08-28
 */
@Service
public class TesttableServiceImpl extends ServiceImpl<TesttableMapper, Testtable> implements ITesttableService {
    private final TesttableMapper testtableMapper;

    @Autowired
    public TesttableServiceImpl(TesttableMapper testtableMapper) {
        this.testtableMapper = testtableMapper;
    }

    @Override
    public Testtable getAllNum() {
        return testtableMapper.selectAllNum();
    }
}



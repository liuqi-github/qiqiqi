package test.thread.demo.mapper;

import org.mapstruct.Mapper;
import test.thread.demo.entity.Testtable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.*;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author mpcoder
 * @since 2020-08-28
 */
@Mapper
public interface TesttableMapper extends BaseMapper<Testtable> {
    public Testtable selectAllNum();
}

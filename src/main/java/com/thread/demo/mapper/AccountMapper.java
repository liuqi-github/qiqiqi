package com.thread.demo.mapper;

import com.thread.demo.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-24
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    public List<Account>  selectAllAccount();
}

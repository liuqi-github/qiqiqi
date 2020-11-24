package com.thread.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.thread.demo.entity.Account;
import com.thread.demo.mapper.AccountMapper;
import com.thread.demo.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-24
 */
@Service
@DS("db1")   //动态切换数据源 本质上还是使用aop切面来切换数据源
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public  List<Account>  seletctAllAccount(){
        return  accountMapper.selectAllAccount();
    }



}

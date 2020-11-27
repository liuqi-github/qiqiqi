package com.thread.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thread.demo.entity.Account;
import com.thread.demo.entity.MessageTable;
import com.thread.demo.entity.TransferOrderTable;
import com.thread.demo.enums.MessageEnum;
import com.thread.demo.enums.MessageTypeEnum;
import com.thread.demo.mapper.AccountMapper;
import com.thread.demo.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thread.demo.service.IMessageTableService;
import com.thread.demo.service.ITransferOrderTableService;
import com.thread.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@Slf4j
@DS("db1")   //动态切换数据源 本质上还是使用aop切面来切换数据源
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    private final AccountMapper accountMapper;
    private final IMessageTableService iMessageTableService;
    private final TransferOrderTableServiceImpl iTransferOrderTableService;


    @Autowired
    public AccountServiceImpl(AccountMapper accountMapper,
                              IMessageTableService iMessageTableService,
                              TransferOrderTableServiceImpl iTransferOrderTableService) {
        this.accountMapper = accountMapper;
        this.iMessageTableService = iMessageTableService;
        this.iTransferOrderTableService = iTransferOrderTableService;
    }

    public  List<Account>  seletctAllAccount(){
        return  accountMapper.selectAllAccount();
    }


    @Transactional
    @DS("master")
    public void  transferAccount(MessageTypeEnum messageTypeEnum, String accountName, String toAccount, Integer money, Account account, Integer messageId) throws Exception {
            //账户转账 转出
            if(account.getPersonMoney() < money){
                log.info(account.getPersonAccount() + "账户余额不足");
                MessageTable messageTable = new MessageTable();
                messageTable.setId(messageId);
                messageTable.setStatus(MessageEnum.FAIL_SEND.getValue());
                messageTable.setMessage("账户内余额不足");
                iMessageTableService.updateById(messageTable);
                throw new Exception();
            }else if(account.getPersonMoney() >= money){
                account.setPersonMoney(account.getPersonMoney() - money);
                update(account, new QueryWrapper<Account>().eq("person_account", accountName));

                TransferOrderTable transferOrderTable = new TransferOrderTable();
                transferOrderTable.setMoney(money);
                transferOrderTable.setMsgId(messageId);
                transferOrderTable.setPersonAccount(accountName);
                transferOrderTable.setToAccount(toAccount);
                transferOrderTable.setAccountCreateTime(LocalDateTime.now());
                iTransferOrderTableService.save(transferOrderTable);

                MessageTable messageTable = new MessageTable();
                messageTable.setId(messageId);
                messageTable.setStatus(MessageEnum.LOCAL_TASK_SUCCESS.getValue());
                messageTable.setMessage(MessageEnum.LOCAL_TASK_SUCCESS.getMemo());
                iMessageTableService.updateById(messageTable);
            }
    }

    //加钱
    @Transactional //切换数据源失效  考虑是事务影响到了切换数据源
    public void  addMoney(TransferOrderTable transferOrderTableMaster, TransferOrderTable transferOrderTableDb1){
        //保证幂等 已经转账过的id直接丢弃
        if(transferOrderTableMaster != null && transferOrderTableDb1 == null){
            Account account = getOneDb1(new QueryWrapper<Account>().eq("person_account", transferOrderTableMaster.getToAccount()));
            account.setPersonMoney(account.getPersonMoney() + transferOrderTableMaster.getMoney());
            updateByIdDb1(account);
            iTransferOrderTableService.saveDb1(transferOrderTableMaster);
        }
    }


    @DS("master")
    public Account getOneMaster(Wrapper<Account> queryWrapper) {
        return accountMapper.selectOne(queryWrapper);
    }

    public Account getOneDb1(Wrapper<Account> queryWrapper) {
        return accountMapper.selectOne(queryWrapper);
    }

    @DS("master")
    public int updateByIdMaster(Account account) {
        return accountMapper.updateById(account);
    }

    public int updateByIdDb1(Account account) {
        return accountMapper.updateById(account);
    }

    @DS("master")
    public boolean saveMaster(Account entity) {
        return super.save(entity);
    }

    public boolean saveDb1(Account entity) {
        return super.save(entity);
    }
}

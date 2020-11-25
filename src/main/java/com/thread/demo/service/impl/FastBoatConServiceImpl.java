package com.thread.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.thread.demo.entity.FastBoatCon;
import com.thread.demo.mapper.FastBoatConMapper;
import com.thread.demo.service.IFastBoatConService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 快舟物流合同表 服务实现类
 * </p>
 *
 * @author mpcoder
 * @since 2020-11-23
 */
@Service
@DS("master")
public class FastBoatConServiceImpl extends ServiceImpl<FastBoatConMapper, FastBoatCon> implements IFastBoatConService {

}

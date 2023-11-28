package com.example.shardingmybatis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shardingmybatis.entity.UserEntity;
import com.example.shardingmybatis.entity.UserEntityTest;
import com.example.shardingmybatis.mapper.UserEntityMapper;
import com.example.shardingmybatis.mapper.UserEntityTestMapper;
import com.example.shardingmybatis.service.UserEntityService;
import com.example.shardingmybatis.service.UserEntityTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:31
 */
@Service
public class UserEntityTestServiceImpl extends ServiceImpl<UserEntityTestMapper,UserEntityTest> implements UserEntityTestService {

    @Override
    @Transactional
    public void batchInsert(List<UserEntityTest> userEntityList) {
        baseMapper.saveBatch(userEntityList);
    }

    @Override
    public UserEntityTest selectByOrderNumberJoin(String orderNumber) {
        return baseMapper.selectByOrderNumberJoinExtend(orderNumber);
    }

    @Override
    public List<UserEntityTest> selectByCrtTimeLimit(Date crtTime) {
        return baseMapper.selectByCrtTimeLimit(crtTime);
    }
}

package com.example.shardingmybatis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shardingmybatis.entity.UserEntity;
import com.example.shardingmybatis.mapper.UserEntityMapper;
import com.example.shardingmybatis.service.UserEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:31
 */
@Service
public class UserEntityServiceImpl extends ServiceImpl<UserEntityMapper,UserEntity> implements UserEntityService {

    @Override
    @Transactional
    public void batchInsert(List<UserEntity> userEntityList) {
        baseMapper.saveBatch(userEntityList);
    }

    @Override
    public UserEntity selectByOrderNumberJoin(String orderNumber) {
        return baseMapper.selectByOrderNumberJoinExtend(orderNumber);
    }

    @Override
    public List<UserEntity> selectByCrtTimeLimit(Date crtTime) {
        return baseMapper.selectByCrtTimeLimit(crtTime);
    }

    @Override
    public UserEntity selectByCrtTime(Date crtTime, String orderNumber) {
        return baseMapper.selectByCrtTime(crtTime,orderNumber);
    }
}

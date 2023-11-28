package com.example.shardingmybatis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shardingmybatis.entity.UserExtend;
import com.example.shardingmybatis.entity.UserExtendTest;
import com.example.shardingmybatis.mapper.UserExtendMapper;
import com.example.shardingmybatis.mapper.UserExtendTestMapper;
import com.example.shardingmybatis.service.UserExtendService;
import com.example.shardingmybatis.service.UserExtendTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:32
 */
@Service
public class UserExtendTestServiceImpl extends ServiceImpl<UserExtendTestMapper, UserExtendTest> implements UserExtendTestService {
    @Override
    @Transactional
    public void batchInsert(List<UserExtendTest> userExtendList) {
        baseMapper.saveBatch(userExtendList);
    }
}

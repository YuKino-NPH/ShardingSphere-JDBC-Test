package com.example.shardingmybatis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.shardingmybatis.entity.UserExtend;
import com.example.shardingmybatis.mapper.UserExtendMapper;
import com.example.shardingmybatis.service.UserExtendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:32
 */
@Service
public class UserExtendServiceImpl extends ServiceImpl<UserExtendMapper, UserExtend> implements UserExtendService {
    @Override
    @Transactional
    public void batchInsert(List<UserExtend> userExtendList) {
        baseMapper.saveBatch(userExtendList);
    }
}

package com.example.shardingmybatis.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.shardingmybatis.entity.UserExtend;
import com.example.shardingmybatis.entity.UserExtendTest;

import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:28
 */
public interface UserExtendTestService extends IService<UserExtendTest> {
    void batchInsert(List<UserExtendTest> userExtendList);
}

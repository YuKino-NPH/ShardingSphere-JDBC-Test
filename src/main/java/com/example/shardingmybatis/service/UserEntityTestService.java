package com.example.shardingmybatis.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.shardingmybatis.entity.UserEntity;
import com.example.shardingmybatis.entity.UserEntityTest;

import java.util.Date;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:28
 */
public interface UserEntityTestService extends IService<UserEntityTest> {
    void batchInsert(List<UserEntityTest> userEntityList);
    UserEntityTest selectByOrderNumberJoin(String orderNumber);
    List<UserEntityTest> selectByCrtTimeLimit(Date crtTime);
}

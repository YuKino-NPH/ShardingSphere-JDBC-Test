package com.example.shardingmybatis.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.shardingmybatis.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:28
 */
public interface UserEntityService extends IService<UserEntity> {
    void batchInsert(List<UserEntity> userEntityList);
    UserEntity selectByOrderNumberJoin(String orderNumber);
    List<UserEntity> selectByCrtTimeLimit(Date crtTime);
    UserEntity selectByCrtTime(Date crtTime,String orderNumber);
}

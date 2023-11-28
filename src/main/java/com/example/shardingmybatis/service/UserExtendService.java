package com.example.shardingmybatis.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.shardingmybatis.entity.UserExtend;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:28
 */
public interface UserExtendService extends IService<UserExtend> {
    void batchInsert(List<UserExtend> userExtendList);
}

package com.example.shardingmybatis.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.shardingmybatis.entity.UserEntity;
import com.example.shardingmybatis.entity.UserExtend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:10
 */
@Mapper
public interface UserExtendMapper extends BaseMapper<UserExtend> {
    void saveBatch(List<UserExtend> userExtendList);
}

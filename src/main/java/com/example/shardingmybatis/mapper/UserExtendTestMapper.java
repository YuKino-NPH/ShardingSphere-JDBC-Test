package com.example.shardingmybatis.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.shardingmybatis.entity.UserExtend;
import com.example.shardingmybatis.entity.UserExtendTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:10
 */
@Mapper
public interface UserExtendTestMapper extends BaseMapper<UserExtendTest> {
    void saveBatch(List<UserExtendTest> userExtendList);
}

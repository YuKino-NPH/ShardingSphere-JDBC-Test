package com.example.shardingmybatis.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.shardingmybatis.entity.UserEntity;
import com.example.shardingmybatis.entity.UserEntityTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/17  10:07
 */
@Mapper
public interface UserEntityTestMapper extends BaseMapper<UserEntityTest> {
    void saveBatch(List<UserEntityTest> userEntityList);
    UserEntityTest selectByOrderNumberJoinExtend(String orderNumber);
    List<UserEntityTest> selectByCrtTimeLimit(Date crtTime);
    List<UserEntityTest> selectByCrtTimeJoinLimit(Date crtTime);
    List<UserEntityTest> selectByCrtTimeAndPhoneJoinLimit(@Param("crtTime") Date crtTime, @Param("phone") String phone);
    List<UserEntityTest> selectByCrtTimeLikePhone(@Param("crtTime") Date crtTime, @Param("phone") String phone);
}

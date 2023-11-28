package com.example.shardingmybatis.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shardingmybatis.entity.UserEntity;
import com.example.shardingmybatis.entity.UserEntityTest;
import com.example.shardingmybatis.entity.UserExtend;
import com.example.shardingmybatis.entity.UserExtendTest;
import com.example.shardingmybatis.mapper.UserEntityMapper;
import com.example.shardingmybatis.mapper.UserEntityTestMapper;
import com.example.shardingmybatis.service.UserEntityService;
import com.example.shardingmybatis.service.UserEntityTestService;
import com.example.shardingmybatis.service.UserExtendService;
import com.example.shardingmybatis.service.UserExtendTestService;
import com.example.shardingmybatis.utils.DateUuidGenerator;
import com.example.shardingmybatis.utils.MyDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/10/16  17:23
 */
@Slf4j
@RestController
public class UserEntityController implements ApplicationContextAware {
    @Autowired
    UserEntityService userEntityService;
    @Autowired
    UserExtendService userExtendService;
    @Autowired
    UserEntityTestService userEntityTestService;
    @Autowired
    UserExtendTestService userExtendTestService;
    @Autowired
    UserEntityMapper userEntityMapper;
    @Autowired
    UserEntityTestMapper userEntityTestMapper;
    ApplicationContext applicationContext;

    @PostMapping("selectByEntity")
    public void selectByEntity(@RequestBody UserEntity user){
        UserEntity userEntity = userEntityService.selectByCrtTime(user.getCrtTime(),user.getOrderNumber());
        System.out.println(userEntity);
    }

    @PostMapping("saveAndExtend")
    public void saveAndExtend(@RequestBody UserEntity user) {
        UserExtend userExtend = new UserExtend();
//        Long extendId = DateUuidGenerator.generateId(user.getCrtTime());
        userExtend.setPhone(user.getPhone());
//        userExtend.setId(extendId);
        userExtend.setUsername(user.getUsername());
        userExtend.setCrtTime(user.getCrtTime());
        userExtend.setOrderNumber(user.getOrderNumber());
        userExtendService.insert(userExtend);
        user.setUserExtendId(userExtend.getId());
//        user.setId(DateUuidGenerator.generateId(user.getCrtTime()));
        userEntityService.insert(user);
    }

    @GetMapping("insertBatch")
    public void insertBatchUser() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse("2023-01-01 00:00:00");
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());

        List<UserExtend> userExtendList = new ArrayList<>();
        List<UserEntity> userEntityList = new ArrayList<>();
        List<UserEntityTest> userEntityTestList = new ArrayList<>();
        List<UserExtendTest> userExtendTestList = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            for (int j = 1; j < 10000; j++) {
                Date randomDate = MyDateUtils.getRandomDate(date);
                UserExtend userExtend = new UserExtend();
                Long extendId = DateUuidGenerator.generateId(randomDate);
                userExtend.setCrtTime(randomDate);
                userExtend.setId(extendId);
                userExtend.setOrderNumber("GNZJ" + yyyyMMdd.format(randomDate) + i + j);
                userExtend.setPhone("15727" + i + j);
                userExtendList.add(userExtend);
                // entity
                UserEntity userEntity = new UserEntity();
                Long userId = DateUuidGenerator.generateId(randomDate);
                userEntity.setUserExtendId(extendId);
                userEntity.setId(userId);
                userEntity.setPhone(userExtend.getPhone());
                userEntity.setUsername(userExtend.getUsername());
                userEntity.setCrtTime(randomDate);
                userEntity.setOrderNumber(userExtend.getOrderNumber());
                userEntityList.add(userEntity);
                saveOriginalDate(userEntity,userExtend,userEntityTestList,userExtendTestList);
            }
            log.info("开始插入");
            long l = System.currentTimeMillis();
            userEntityService.batchInsert(userEntityList);
            userExtendService.batchInsert(userExtendList);
            long l1 = System.currentTimeMillis();
            log.info("分表插入时间:"+(l1-l));
            userEntityTestService.batchInsert(userEntityTestList);
            userExtendTestService.batchInsert(userExtendTestList);
            log.info("插入时间:"+(System.currentTimeMillis()-l1));

            clearList(userEntityList,userExtendList,userEntityTestList,userExtendTestList);
            log.info("已插入" + (i * 10000));
        }
    }

    private void clearList(List<UserEntity> userEntityList, List<UserExtend> userExtendList, List<UserEntityTest> userEntityTestList, List<UserExtendTest> userExtendTestList) {
        userEntityList.clear();
        userExtendList.clear();
        userEntityTestList.clear();
        userExtendTestList.clear();
    }

    private void saveOriginalDate(UserEntity userEntity, UserExtend userExtend, List<UserEntityTest> userEntityTestList, List<UserExtendTest> userExtendTestList) {
        UserEntityTest userEntityTest = UserEntityTest.from(userEntity);
        UserExtendTest userExtendTest = UserExtendTest.from(userExtend);
        userEntityTestList.add(userEntityTest);
        userExtendTestList.add(userExtendTest);
    }
    // 根据主键查询
    @GetMapping("/selectById")
    public void selectById(Long id){
        long l = System.currentTimeMillis();
        userEntityService.selectById(id);
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("/selectByIdTest")
    public void selectByIdTest(Long id){
        long l = System.currentTimeMillis();
        userEntityTestService.selectById(id);
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }

    @GetMapping("selectByIndex")
    public void selectByIndex(String index){
        long l = System.currentTimeMillis();
        UserEntity userEntity = userEntityService.selectOne(new EntityWrapper<UserEntity>().eq("order_number", index));
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("selectByIndexTest")
    public void selectByIndexTest(String index){
        long l = System.currentTimeMillis();
        UserEntityTest userEntityTest = userEntityTestService.selectOne(new EntityWrapper<UserEntityTest>().eq("order_number", index));
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    // 联表查询测试
    @GetMapping("selectJoin")
    public void selectJoin(String index){
        long l = System.currentTimeMillis();
        UserEntity userEntity = userEntityService.selectByOrderNumberJoin(index);
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("selectJoinTest")
    public void selectJoinTest(String index){
        long l = System.currentTimeMillis();
        UserEntityTest userEntityTest = userEntityTestService.selectByOrderNumberJoin(index);
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    // 分页查询
    @GetMapping("selectLimit")
    public void selectLimit(String date) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        List<UserEntity> userEntities = userEntityService.selectByCrtTimeLimit(parse);
        System.out.println(userEntities.get(0).getId());
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("selectLimitTest")
    public void selectLimitTest(String date) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        List<UserEntityTest> userEntityTests = userEntityTestService.selectByCrtTimeLimit(parse);
        System.out.println(userEntityTests.get(0).getId());
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    //分页联表查询
    @GetMapping("selectLimitJoin")
    public void selectLimitJoin(String date) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        List<UserEntity> userEntities = userEntityMapper.selectByCrtTimeJoinLimit(parse);
        System.out.println(userEntities.get(0).getId());
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("selectLimitJoinTest")
    public void selectLimitJoinTest(String date) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        List<UserEntityTest> userEntities = userEntityTestMapper.selectByCrtTimeJoinLimit(parse);
        System.out.println(userEntities.get(0).getId());
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    //分页联表根据Phone查询
    @GetMapping("selectLimitJoinByPhone")
    public void selectLimitJoinByPhone(String date,String phone) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        List<UserEntity> userEntities = userEntityMapper.selectByCrtTimeAndPhoneJoinLimit(parse,phone);
        System.out.println(userEntities.get(0).getId());
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("selectLimitJoinByPhoneTest")
    public void selectLimitJoinByPhoneTest(String date,String phone) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        List<UserEntityTest> userEntities = userEntityTestMapper.selectByCrtTimeAndPhoneJoinLimit(parse,phone);
        System.out.println(userEntities.get(0).getId());
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    //分页联表根据Phone模糊查询
    @GetMapping("selectByCrtTimeLikePhone")
    public void selectByCrtTimeLikePhone(String date,String phone) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        phone="%"+phone+"%";
        List<UserEntity> userEntities = userEntityMapper.selectByCrtTimeLikePhone(parse,phone);
        if (userEntities.size()>0){
            System.out.println(userEntities.get(0).getId());
        }
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("selectByCrtTimeLikePhoneTest")
    public void selectByCrtTimeLikePhoneTest(String date,String phone) throws ParseException {
        SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyyMMdd.parse(date);
        long l = System.currentTimeMillis();
        phone="%"+phone+"%";
        List<UserEntityTest> userEntities = userEntityTestMapper.selectByCrtTimeLikePhone(parse,phone);
        System.out.println(userEntities.get(0).getId());
        log.info(String.valueOf(System.currentTimeMillis()-l));
    }
    @GetMapping("test")
    public void test(){
//        String sql = "select order_id from t_order where status = 'OK'";
//        ShardingSphereDataSource dataSource = (ShardingSphereDataSource) applicationContext.getBean("shardingSphereDataSource");
//        MetaDataContexts metaDataContexts = dataSource.getContextManager().getMetaDataContexts();
//        DatabaseType databaseType = metaDataContexts.getMetaData(dataSource.getSchemaName()).getResource().getDatabaseType();
//        long l = System.currentTimeMillis();
//        ShardingSphereSQLParserEngine sqlParserEngine = new ShardingSphereSQLParserEngine(
//                DatabaseTypeRegistry.getTrunkDatabaseTypeName(databaseType),metaDataContexts.getProps());
//        SQLStatement sqlStatement = sqlParserEngine.parse(sql, true);
//        System.out.println(System.currentTimeMillis()-l);
//
//        ShardingSphereSQLParserEngine sqlParserEngine = new ShardingSphereSQLParserEngine();
        System.out.println("1111");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}

package com.example.shardingmybatis;

import org.apache.shardingsphere.infra.database.type.DatabaseTypeRegistry;
import org.apache.shardingsphere.infra.database.type.dialect.MySQLDatabaseType;
import org.apache.shardingsphere.infra.parser.ShardingSphereSQLParserEngine;
import org.apache.shardingsphere.sql.parser.api.CacheOption;
import org.apache.shardingsphere.sql.parser.api.SQLParserEngine;
import org.apache.shardingsphere.sql.parser.core.ParseContext;
import org.junit.jupiter.api.Test;

/**
 * @author: 聂裴涵
 * @date: 2023/10/30  19:00
 */
public class SQLParserEngineTest {
    @Test
    public void test(){
        String sql = "select order_id from t_order where status = 'OK'";
        CacheOption cacheOption = new CacheOption(128, 1024L, 4);
        SQLParserEngine parserEngine = new SQLParserEngine("MySQL", cacheOption, false);
        ParseContext parseContext = parserEngine.parse(sql, false);
        System.out.println(parseContext.getParseTree());
//        ShardingSphereSQLParserEngine sqlParserEngine = new ShardingSphereSQLParserEngine(
//                DatabaseTypeRegistry.getTrunkDatabaseTypeName(MySQLDatabaseType.class, metaDataContexts.getProps());
//        sqlStatement = sqlParserEngine.parse(sql, true);
//
//        ShardingSphereSQLParserEngine sqlParserEngine = new ShardingSphereSQLParserEngine();
        System.out.println(parseContext);
    }
}

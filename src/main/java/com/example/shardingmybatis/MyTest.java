package com.example.shardingmybatis;


import org.apache.shardingsphere.infra.parser.sql.SQLStatementParserExecutor;
import org.apache.shardingsphere.sql.parser.api.CacheOption;
import org.apache.shardingsphere.sql.parser.sql.common.segment.generic.ParameterMarkerSegment;
import org.apache.shardingsphere.sql.parser.sql.common.statement.SQLStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.mysql.dml.MySQLSelectStatement;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

/**
 * @author: 聂裴涵
 * @date: 2023/11/6  16:10
 */
public class MyTest {
    @Test
    public void test(){
//        List<String> dataNodes = new InlineExpressionParser("tms_uat.base_order_$->{[2020,2021,2022,2023]}0$->{[1,4,7]},tms_uat.base_order_$->{[2019,2020,2021,2022]}$->{[10]}").splitAndEvaluate();
//        System.out.println(dataNodes);
    }
    @Test
    public void testSQLStatementParserExecutor() {
//        String sql = "SELECT bo.ID FROM BASE_ORDER bo WHERE bo.NAME=CAST(? as signed)";
//        SQLStatementParserExecutor parserExecutor = new SQLStatementParserExecutor(new MySQLDatabaseType(), new CacheOption(1024, 1024), false);
//        SQLStatement parse = parserExecutor.parse(sql);
////        System.out.println(parse);
//        MySQLSelectStatement sqlStatement=(MySQLSelectStatement) parse;
//        Collection<ParameterMarkerSegment> segments = sqlStatement.getParameterMarkerSegments();
////        for (ParameterMarkerSegment segment : segments) {
////        }
//        System.out.println(parse.getParameterCount());
    }
}

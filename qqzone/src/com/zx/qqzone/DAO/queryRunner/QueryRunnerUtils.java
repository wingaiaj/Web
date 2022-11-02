package com.zx.qqzone.DAO.queryRunner;

import org.apache.commons.dbutils.QueryRunner;

/**
 * @ClassName QueryRunnerUtils
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 9:54
 * @Version 1.0
 */
public class QueryRunnerUtils {
    private static final ThreadLocal<QueryRunner> threadLocalQuery = new ThreadLocal<>();
    private static final QueryRunner queryRunner = new QueryRunner();

    //获取queryRunner对象
    public static QueryRunner getQueryRunner() {
        if (threadLocalQuery.get() == null) {

            threadLocalQuery.set(queryRunner);
        }

        return threadLocalQuery.get();
    }

}

package com.dataart.hibernate.demo.util.sqltracker;

public class QueryCountInfoHolder {

    private static ThreadLocal<QueryCountInfo> queryInfoHolder = ThreadLocal.withInitial(QueryCountInfo::new);

    public static QueryCountInfo getQueryInfo() {
        return queryInfoHolder.get();
    }

    public static void clear() {
        queryInfoHolder.get().clear();
    }
}

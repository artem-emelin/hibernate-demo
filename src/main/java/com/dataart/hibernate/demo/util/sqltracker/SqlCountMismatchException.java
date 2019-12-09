package com.dataart.hibernate.demo.util.sqltracker;

public class SqlCountMismatchException extends RuntimeException {
    public SqlCountMismatchException(String statement, int expectedCount, int actualCount) {
        super("Expected " + statement + " query count : " + expectedCount + ", but was : " + actualCount);
    }
}

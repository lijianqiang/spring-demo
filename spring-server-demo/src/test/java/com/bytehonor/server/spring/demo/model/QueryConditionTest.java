package com.bytehonor.server.spring.demo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bytehonor.sdk.lang.spring.query.QueryCondition;

public class QueryConditionTest {

    @Test
    public void test() {
        QueryCondition condition = QueryCondition.and().eq(UserProfile::getCreateAt, 0L);

        Assertions.assertTrue(condition.has(UserProfile::getCreateAt), "test");
    }
}

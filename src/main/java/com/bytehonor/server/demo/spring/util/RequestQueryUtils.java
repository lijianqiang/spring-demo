package com.bytehonor.server.demo.spring.util;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.bytehonor.sdk.server.spring.getter.RequestGetter;
import com.bytehonor.sdk.starter.jdbc.query.QueryCondition;

public class RequestQueryUtils {

    public static QueryCondition create(HttpServletRequest request) {
        Objects.requireNonNull(request, "request");
        int offset = RequestGetter.getOffset(request);
        int limit = RequestGetter.getLimit(request);

        return QueryCondition.create(offset, limit);
    }
}

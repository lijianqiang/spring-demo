package com.bytehonor.server.demo.spring.service;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.util.UuidUtils;
import com.bytehonor.sdk.starter.jdbc.JdbcConfig;
import com.bytehonor.server.demo.spring.TestWrapper;
import com.bytehonor.server.demo.spring.model.UserProfile;

@SpringBootTest
public class UserProfileServiceTest3 {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceTest3.class);

    @Autowired
    private UserProfileService userProfileService;

    @BeforeAll
    public static void setUp() {
        JdbcConfig.setInfoEnabled(true);
    }

    @Test
    public void testDistinct() {
        userProfileService.delete(QueryCondition.and().egt(UserProfile::getAge, 0));

        UserProfile model = new UserProfile();
        model.setPhone("phone");
        model.setIncome("");
        model.setGender(1);

        int top = 10;
        for (int i = 0; i < 200; i++) {
            model.setAge(i % top);
            model.setName("name" + i);
            model.setUuid(UuidUtils.getSimple());
            userProfileService.insert(model);
        }

        // SELECT DISTINCT(age) FROM tbl_user_profile WHERE age >= ?
        List<Integer> list = userProfileService.distinctAge(QueryCondition.all().egt(UserProfile::getAge, 0));
        for (Integer val : list) {
            LOG.info("val:{}", val);
        }
        LOG.info("testDistinct list:{}", list.size());
        TestWrapper.assertTrue("*testDistinct*", list.size() == top);
    }
}

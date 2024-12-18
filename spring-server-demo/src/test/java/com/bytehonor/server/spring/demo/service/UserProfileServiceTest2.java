package com.bytehonor.server.spring.demo.service;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.util.UuidUtils;
import com.bytehonor.server.spring.demo.TestWrapper;
import com.bytehonor.server.spring.demo.model.UserProfile;

@SpringBootTest
public class UserProfileServiceTest2 {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceTest2.class);

    @Autowired
    private UserProfileService userProfileService;

    private static UserProfile model;

    @BeforeAll
    public static void setUp() {
        long now = System.currentTimeMillis();
        int i = (int) (now % 100);
        model = new UserProfile();
        model.setAge(i);
        model.setName("" + i);
        model.setPhone("phone" + now);
        model.setIncome("");
        model.setGender(1);
        model.setOccupation("occupation");
    }

    @Test
    public void testListAll() {
        int total = 3000;
        for (int i = 0; i < total; i++) {
            model.setAge(i);
            model.setName("name" + i);
            model.setUuid(UuidUtils.getSimple());
            userProfileService.insert(model);
        }
        List<UserProfile> list = userProfileService.list(QueryCondition.all());
        int size = list.size();
        LOG.info("list:{}", size);
        TestWrapper.assertTrue("*testListByCondition*", size >= total);
    }
}

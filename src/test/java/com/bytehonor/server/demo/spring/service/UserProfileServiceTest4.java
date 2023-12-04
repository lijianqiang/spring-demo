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
import com.bytehonor.sdk.starter.jdbc.model.GroupByItem;
import com.bytehonor.server.demo.spring.TestWrapper;
import com.bytehonor.server.demo.spring.model.UserProfile;

@SpringBootTest
public class UserProfileServiceTest4 {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceTest4.class);

    @Autowired
    private UserProfileService userProfileService;

    @BeforeAll
    public static void setUp() {
        JdbcConfig.setInfoEnabled(true);
    }

    @Test
    public void testGroupCount() {

        userProfileService.delete(QueryCondition.and().egt(UserProfile::getAge, 0));

        UserProfile model = new UserProfile();
        model.setPhone("phone");
        model.setIncome("");
        model.setGender(1);

        int top = 50;
        for (int i = 0; i < 200; i++) {
            model.setAge(i % top);
            model.setName("name" + model.getAge());
            model.setUuid(UuidUtils.getSimple());
            userProfileService.insert(model);
        }

        // SELECT `age` AS `key`, COUNT(id) AS `value` FROM tbl_user_profile WHERE age <
        // ? AND age >= ? GROUP BY `age`
        List<GroupByItem> list = userProfileService
                .groupCount(QueryCondition.all().lt(UserProfile::getAge, top).egt(UserProfile::getAge, 0));
        for (GroupByItem item : list) {
            LOG.info("key:{}, value:{}", item.getKey(), item.getValue());
        }
        LOG.info("testGroupCount list:{}", list.size());
        TestWrapper.assertTrue("*testGroupCount*", list.size() == top);
    }
}

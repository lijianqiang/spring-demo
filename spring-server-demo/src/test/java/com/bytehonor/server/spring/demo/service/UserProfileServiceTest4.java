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
import com.bytehonor.sdk.starter.jdbc.JdbcConfig;
import com.bytehonor.sdk.starter.jdbc.model.GroupCountItem;
import com.bytehonor.server.spring.demo.TestWrapper;
import com.bytehonor.server.spring.demo.model.UserProfile;

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

        int top = 20;
        for (int i = 0; i < 195; i++) {
            model.setAge(i % top);
            model.setName("name" + model.getAge());
            model.setUuid(UuidUtils.getSimple());
            userProfileService.insert(model);
        }

        // SELECT `name` AS `value`, COUNT(id) AS `size` FROM tbl_user_profile WHERE age
        // >= ? GROUP BY `name` ORDER BY `size` ASC
        QueryCondition condition = QueryCondition.all().egt(UserProfile::getAge, 0).asc(GroupCountItem::getSize);
        List<GroupCountItem> list = userProfileService.groupCount(condition);
        for (GroupCountItem item : list) {
            LOG.info("value:{}, size:{}", item.getValue(), item.getSize());
        }
        LOG.info("testGroupCount list:{}", list.size());
        TestWrapper.assertTrue("*testGroupCount*", list.size() == top);
    }
}

package com.bytehonor.server.demo.spring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.bytehonor.sdk.lang.spring.util.UuidUtils;
import com.bytehonor.sdk.starter.jdbc.JdbcConfig;
import com.bytehonor.server.demo.spring.TestWrapper;
import com.bytehonor.server.demo.spring.model.UserProfile;

@SpringBootTest
public class UserProfileServiceTest5 {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceTest5.class);

    @Autowired
    private UserProfileService userProfileService;

    @BeforeAll
    public static void setUp() {
        JdbcConfig.setInfoEnabled(true);
    }

    @Test
    public void testSetEmptyString() {
        UserProfile profile = new UserProfile();
        profile.setPhone("phone");
        profile.setIncome("");
        profile.setGender(1);
        profile.setAge(999);
        profile.setName("test_update_phone_empty");
        profile.setUuid(UuidUtils.getSimple());
        profile = userProfileService.insert(profile);

        UserProfile exist = userProfileService.get(profile.getId());
        LOG.info("1 phone:{}", exist.getPhone());

        UserProfile model = new UserProfile();
        model.setId(profile.getId());
        model.setPhone("");
        userProfileService.update(model);

        exist = userProfileService.get(profile.getId());
        LOG.info("2 phone:{}", exist.getPhone());

        TestWrapper.assertTrue("*testSetEmptyString*", SpringString.isEmpty(exist.getPhone()));
    }
}

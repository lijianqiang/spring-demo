package com.bytehonor.server.spring.demo.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.base.spring.response.DataList;
import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.server.spring.annotation.ResponseWrap;
import com.bytehonor.sdk.server.spring.web.request.RequestParser;
import com.bytehonor.server.spring.demo.model.UserProfile;
import com.bytehonor.server.spring.demo.service.UserProfileService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserProfileService userProfileService;

    @ResponseWrap
    @GetMapping("/profiles")
    @ResponseBody
    public DataList<UserProfile> listUserProfile(HttpServletRequest request) {
        LOG.info("listUserProfile");
        QueryCondition condition = RequestParser.and(UserProfile.class, request);
        DataList<UserProfile> result = new DataList<UserProfile>();
        List<UserProfile> list = userProfileService.list(condition);
        result.setList(list);
        if (condition.counted()) {
            int total = userProfileService.count(condition);
            result.setTotal(total);
        }
        return result;
    }

    @GetMapping("/profile/{id}")
    @ResponseBody
    public UserProfile getUserProfile(HttpServletRequest request, @PathVariable Long id) {
        LOG.info("getUserProfile id:{}", id);
        UserProfile model = userProfileService.get(id);
        Objects.requireNonNull(model, "id");

        return model;
    }

    @PutMapping("/profile/{id}")
    @ResponseBody
    public UserProfile updateUserProfile(HttpServletRequest request, @PathVariable Long id,
            @RequestBody UserProfile body) {
        LOG.info("updateUserProfile id:{}", id);
        UserProfile exist = userProfileService.get(id);
        Objects.requireNonNull(exist, "id");

        body.setId(id);
        userProfileService.update(body);

        return userProfileService.get(id);
    }

    @PostMapping("/profile")
    @ResponseBody
    public UserProfile insertUserProfile(HttpServletRequest request, @RequestBody UserProfile body) {
        LOG.info("insertUserProfile");
        UserProfile model = userProfileService.insert(body);
        if (model == null) {
            throw new RuntimeException("save failed");
        }
        return model;
    }

}

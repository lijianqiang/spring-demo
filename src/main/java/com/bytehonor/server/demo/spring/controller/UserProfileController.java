package com.bytehonor.server.demo.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bytehonor.sdk.define.spring.query.QueryCondition;
import com.bytehonor.sdk.define.spring.result.DataListVO;
import com.bytehonor.sdk.server.spring.getter.RequestGetter;
import com.bytehonor.server.demo.spring.model.UserProfile;
import com.bytehonor.server.demo.spring.service.UserProfileService;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(method = { RequestMethod.GET }, value = "/profile")
    @ResponseBody
    public DataListVO<UserProfile> listUserProfile(HttpServletRequest request) {
        LOG.info("listUserProfile");
        QueryCondition condition = RequestGetter.create(request);
        List<UserProfile> list = userProfileService.list(condition);
        int total = userProfileService.count(condition);
        DataListVO<UserProfile> result = new DataListVO<UserProfile>();
        result.setList(list);
        result.setTotal(total);
        return result;
    }

    @RequestMapping(method = { RequestMethod.GET }, value = "/profile/{id}")
    @ResponseBody
    public UserProfile getUserProfile(HttpServletRequest request, @PathVariable Long id) {
        LOG.info("getUserProfile id:{}", id);
        UserProfile model = userProfileService.get(id);
        if (model == null) {
            throw new RuntimeException("UserProfile不存在");
        }

        return model;
    }

    @RequestMapping(method = { RequestMethod.POST }, value = "/profile")
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

package com.bytehonor.server.demo.spring.service;

import java.util.List;

import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.server.demo.spring.model.UserProfile;

public interface UserProfileService {

    public boolean delete(Long id);

    public UserProfile insert(UserProfile model);

    public UserProfile get(Long id);

    public UserProfile getByUuid(String uuid);

    public boolean update(UserProfile model);

    public int count(QueryCondition condition);

    public List<UserProfile> list(QueryCondition condition);

    public void save(UserProfile model);

    public List<Integer> distinctAge(QueryCondition condition);

}

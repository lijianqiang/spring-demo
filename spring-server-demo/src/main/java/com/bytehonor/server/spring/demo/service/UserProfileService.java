package com.bytehonor.server.spring.demo.service;

import java.util.List;

import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.starter.jdbc.model.GroupCountItem;
import com.bytehonor.server.spring.demo.model.UserProfile;

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

    public List<GroupCountItem> groupCount(QueryCondition condition);

    public int delete(QueryCondition condition);

}

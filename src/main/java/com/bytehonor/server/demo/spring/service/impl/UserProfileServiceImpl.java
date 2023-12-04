package com.bytehonor.server.demo.spring.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.util.UuidUtils;
import com.bytehonor.sdk.starter.jdbc.model.GroupCountItem;
import com.bytehonor.server.demo.spring.dao.UserProfileDao;
import com.bytehonor.server.demo.spring.model.UserProfile;
import com.bytehonor.server.demo.spring.service.UserProfileService;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public boolean delete(Long id) {
        Objects.requireNonNull(id, "id");
        return userProfileDao.deleteById(id);
    }

    @Override
    public int delete(QueryCondition condition) {
        Objects.requireNonNull(condition, "condition");
        return userProfileDao.delete(condition);
    }

    @Override
    public UserProfile insert(UserProfile model) {
        Objects.requireNonNull(model, "model");

        long now = System.currentTimeMillis();
        if (model.getUpdateAt() == null) {
            model.setUpdateAt(now);
        }
        if (model.getCreateAt() == null) {
            model.setCreateAt(now);
        }
        if (model.getUuid() == null) {
            model.setUuid(UuidUtils.getSimple());
        }
        return userProfileDao.insert(model);
    }

    @Override
    public UserProfile get(Long id) {
        Objects.requireNonNull(id, "id");
        return userProfileDao.get(id);
    }

    @Override
    public UserProfile getByUuid(String uuid) {
        Objects.requireNonNull(uuid, "uuid");

        QueryCondition condition = QueryCondition.one().eq(UserProfile::getUuid, uuid);
        List<UserProfile> result = list(condition);
        return DataAccessUtils.uniqueResult(result);
    }

    @Override
    public boolean update(UserProfile model) {
        Objects.requireNonNull(model, "model");
        Objects.requireNonNull(model.getId(), "id");

        return userProfileDao.update(model) > 0;
    }

    @Override
    public int count(QueryCondition condition) {
        Objects.requireNonNull(condition, "condition");
        return userProfileDao.count(condition);
    }

    @Override
    public List<UserProfile> list(QueryCondition condition) {
        Objects.requireNonNull(condition, "condition");
        return userProfileDao.list(condition);
    }

    @Override
    public void save(UserProfile model) {
        Objects.requireNonNull(model, "model");
        UserProfile exist = getByUuid(model.getUuid());
        if (exist != null) {
            model.setId(exist.getId());
            update(model);
        } else {
            insert(model);
        }
    }

    @Override
    public List<Integer> distinctAge(QueryCondition condition) {
        return userProfileDao.integers(UserProfile::getAge, condition);
    }

    @Override
    public List<GroupCountItem> groupCount(QueryCondition condition) {
        return userProfileDao.groupCount(UserProfile::getName, condition);
    }

}

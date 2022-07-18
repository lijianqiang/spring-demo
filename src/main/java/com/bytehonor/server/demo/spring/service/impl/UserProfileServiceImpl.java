package com.bytehonor.server.demo.spring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.bytehonor.sdk.define.spring.constant.HttpConstants;
import com.bytehonor.sdk.define.spring.query.QueryCondition;
import com.bytehonor.sdk.lang.spring.util.UuidUtils;
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
        return userProfileDao.delete(id);
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

        QueryCondition condition = QueryCondition.one().eq("uuid", uuid);
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
    public List<UserProfile> listAll(QueryCondition condition) {
        Objects.requireNonNull(condition, "condition");
        int total = count(condition);
        if (total < 1) {
            return new ArrayList<UserProfile>();
        }
        List<UserProfile> result = new ArrayList<UserProfile>(total * 2);
        List<UserProfile> part = new ArrayList<UserProfile>();
        int start = 0;
        int limit = HttpConstants.LIMIT_MAX_TOP;
        while (start < total) {
            condition.setLimit(limit);
            condition.setOffset(start);
            part = list(condition);
            if (part == null || part.isEmpty()) {
                break;
            }
            result.addAll(part);
            start += limit;
        }
        return result;
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
        return userProfileDao.distinctAge(condition);
    }

}

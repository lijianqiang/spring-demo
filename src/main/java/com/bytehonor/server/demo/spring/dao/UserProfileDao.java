package com.bytehonor.server.demo.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bytehonor.sdk.lang.spring.function.ClassGetter;
import com.bytehonor.sdk.lang.spring.function.getter.GetInteger;
import com.bytehonor.sdk.lang.spring.query.QueryCondition;
import com.bytehonor.sdk.starter.jdbc.dao.JdbcProxyDao;
import com.bytehonor.sdk.starter.jdbc.model.GroupCountItem;
import com.bytehonor.sdk.starter.jdbc.model.ModelGetter;
import com.bytehonor.sdk.starter.jdbc.model.ModelGetterMapper;
import com.bytehonor.sdk.starter.jdbc.model.ModelSetter;
import com.bytehonor.sdk.starter.jdbc.model.ModelSetterMapper;
import com.bytehonor.server.demo.spring.model.UserProfile;

@Repository
public class UserProfileDao {

    @Autowired
    private JdbcProxyDao jdbcProxyDao;

    private final ModelSetterMapper<UserProfile> setterMapper = new ModelSetterMapper<UserProfile>() {

        @Override
        public ModelSetter<UserProfile> create(ResultSet rs) throws SQLException {
            ModelSetter<UserProfile> setter = ModelSetter.of(UserProfile::new, rs);

            setter.add(UserProfile::setId);

            setter.add(UserProfile::setUuid);
            setter.add(UserProfile::setName);
            setter.add(UserProfile::setAge);
            setter.add(UserProfile::setGender);
            setter.add(UserProfile::setIncome);

            setter.add(UserProfile::setPhone);
            setter.add(UserProfile::setOccupation);

            setter.add(UserProfile::setUpdateAt);
            setter.add(UserProfile::setCreateAt);

            return setter;
        }
    };

    private final ModelGetterMapper<UserProfile> getterMapper = new ModelGetterMapper<UserProfile>() {

        @Override
        public ModelGetter<UserProfile> create(UserProfile model) {
            ModelGetter<UserProfile> getter = ModelGetter.of(model);

            getter.add(UserProfile::getUuid);
            getter.add(UserProfile::getName);
            getter.add(UserProfile::getAge);
            getter.add(UserProfile::getGender);
            getter.add(UserProfile::getIncome);

            getter.add(UserProfile::getPhone);
            getter.add(UserProfile::getOccupation);
            return getter;
        }

    };

    public boolean deleteById(Long id) {
        return jdbcProxyDao.deleteById(UserProfile.class, id) > 0;
    }

    public int delete(QueryCondition condition) {
        return jdbcProxyDao.delete(UserProfile.class, condition);
    }

    public UserProfile insert(UserProfile model) {
        long id = jdbcProxyDao.insert(model, getterMapper);
        model.setId(id);
        return model;
    }

    public UserProfile get(Long id) {
        return jdbcProxyDao.queryById(UserProfile.class, id, setterMapper);
    }

    public int update(UserProfile model) {
        return jdbcProxyDao.updateById(model, model.getId(), getterMapper);
    }

    public int count(QueryCondition condition) {
        return jdbcProxyDao.count(UserProfile.class, condition);
    }

    public List<UserProfile> list(QueryCondition condition) {
        return jdbcProxyDao.query(UserProfile.class, condition, setterMapper);
    }

    public List<Integer> integers(GetInteger<UserProfile> getter, QueryCondition condition) {
        return jdbcProxyDao.integers(UserProfile.class, getter, condition);
    }

    public List<GroupCountItem> groupCount(ClassGetter<UserProfile, ?> getter, QueryCondition condition) {
        return jdbcProxyDao.groupCount(UserProfile.class, getter, condition);
    }
}

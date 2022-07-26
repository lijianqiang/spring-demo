package com.bytehonor.server.demo.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bytehonor.sdk.define.spring.query.QueryCondition;
import com.bytehonor.sdk.starter.jdbc.dao.JdbcProxyDao;
import com.bytehonor.sdk.starter.jdbc.model.ModelConvertMapper;
import com.bytehonor.sdk.starter.jdbc.model.ModelGetterGroup;
import com.bytehonor.sdk.starter.jdbc.result.Results;
import com.bytehonor.server.demo.spring.model.UserProfile;

@Repository
public class UserProfileDao {

    @Autowired
    private JdbcProxyDao jdbcProxyDao;

    private final RowMapper<UserProfile> rowMapper = new RowMapper<UserProfile>() {

        @Override
        public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserProfile model = new UserProfile();
            model.setId(Results.longer(rs, "id"));

            model.setUuid(Results.string(rs, "uuid"));
            model.setName(Results.string(rs, "name"));
            model.setAge(Results.integer(rs, "age"));
            model.setGender(Results.integer(rs, "gender"));
            model.setIncome(Results.string(rs, "income"));

            model.setPhone(Results.string(rs, "phone"));
            model.setOccupation(Results.string(rs, "occupation"));

            model.setUpdateAt(Results.longer(rs, "updateAt"));
            model.setCreateAt(Results.longer(rs, "createAt"));
            return model;
        }
    };

    private final ModelConvertMapper<UserProfile> convertMapper = new ModelConvertMapper<UserProfile>() {

        @Override
        public ModelGetterGroup<UserProfile> create() {

            ModelGetterGroup<UserProfile> group = ModelGetterGroup.create(UserProfile.class);

            group.add("uuid", UserProfile::getUuid);
            group.add("name", UserProfile::getName);
            group.add("age", UserProfile::getAge);
            group.add("gender", UserProfile::getGender);
            group.add("income", UserProfile::getIncome);

            group.add("phone", UserProfile::getPhone);
            group.add("occupation", UserProfile::getOccupation);

            return group;
        }

    };

    public boolean delete(Long id) {
        return jdbcProxyDao.deleteById(UserProfile.class, id) > 0;
    }

    public UserProfile insert(UserProfile model) {
        long id = jdbcProxyDao.insert(model, convertMapper);
        model.setId(id);
        return model;
    }

    public UserProfile get(Long id) {
        return jdbcProxyDao.queryById(UserProfile.class, id, rowMapper);
    }

    public int update(UserProfile model) {
        return jdbcProxyDao.updateById(model, model.getId(), convertMapper);
    }

    public int count(QueryCondition condition) {
        return jdbcProxyDao.count(UserProfile.class, condition);
    }

    public List<UserProfile> list(QueryCondition condition) {
        return jdbcProxyDao.query(UserProfile.class, condition, rowMapper);
    }

    public List<Integer> distinctAge(QueryCondition condition) {
        return jdbcProxyDao.integers(UserProfile.class, "age", condition);
    }

}

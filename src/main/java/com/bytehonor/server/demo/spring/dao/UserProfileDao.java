package com.bytehonor.server.demo.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bytehonor.sdk.define.spring.query.QueryCondition;
import com.bytehonor.sdk.starter.jdbc.dao.JdbcProxyDao;
import com.bytehonor.sdk.starter.jdbc.model.ModelConvertMapper;
import com.bytehonor.sdk.starter.jdbc.model.ModelGetterGroup;
import com.bytehonor.server.demo.spring.model.UserProfile;

@Repository
public class UserProfileDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileDao.class);

    @Autowired
    private JdbcProxyDao jdbcProxyDao;

    private final RowMapper<UserProfile> rowMapper = new RowMapper<UserProfile>() {

        @Override
        public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserProfile model = new UserProfile();
            model.setId(rs.getLong("id"));

            model.setUuid(rs.getString("uuid"));
            model.setName(rs.getString("name"));
            model.setAge(rs.getInt("age"));
            model.setGender(rs.getInt("gender"));
            model.setIncome(rs.getString("income"));

            model.setPhone(rs.getString("phone"));
            model.setOccupation(rs.getString("occupation"));

            model.setUpdateAt(rs.getLong("update_at"));
            model.setCreateAt(rs.getLong("create_at"));
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
        LOG.debug("insert id:{}", id);
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

}

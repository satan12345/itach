package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql="INSERT INTO TBL_USER(USER_NAME,AGE)VALUES(?,?)";
        jdbcTemplate.update(sql, UUID.randomUUID().toString().substring(0,5),18);

    }
}

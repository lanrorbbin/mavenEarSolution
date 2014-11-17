package cn.gov.dd.tax.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by lan on 10/29/14.
 */
public abstract class BaseDao {

    protected JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

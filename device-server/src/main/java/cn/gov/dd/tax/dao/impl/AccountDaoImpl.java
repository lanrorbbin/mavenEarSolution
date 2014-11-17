package cn.gov.dd.tax.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import cn.gov.dd.tax.dao.AccountDao;
import cn.gov.dd.tax.module.Account;
import cn.gov.dd.tax.sql.MySQL;

/**
 * Created by lan on 10/29/14.
 */
@Slf4j
public class AccountDaoImpl extends BaseDao implements AccountDao, MySQL.Account {
    @Override
    public Account getAccountByName(final String accountName) {
        ResultSetExtractor<Account> rse = new ResultSetExtractor<Account>() {
            @Override
            public Account extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                while(resultSet.next()) {
                    return extractAccount(resultSet);
                }
                return null;
            }
        };

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, accountName);
            }
        };
        return jdbcTemplate.query(BY_NAME, pss, rse);
    }

    @Override
    public Account getAccountById(final String id) {
        ResultSetExtractor<Account> rse = new ResultSetExtractor<Account>() {
            @Override
            public Account extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                while(resultSet.next()) {
                    return extractAccount(resultSet);
                }
                return null;
            }
        };

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        };
        return jdbcTemplate.query(BY_ID, pss, rse);
    }

    @Override
    public int updateAccount(final Account account) {
    	/* id, accountname, password, salt, createtime, status, token, clienttype */
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
            	// For insert
                ps.setString(1, account.getId());
                ps.setString(2, account.getAccountName());
                ps.setString(3, account.getPassword());
                ps.setString(4,  account.getSalt());
                ps.setTimestamp(5, new Timestamp(account.getCreateTime().getTime()));
                ps.setInt(6, account.getStatus());
                ps.setString(7, account.getToken());
                ps.setString(8, account.getClientType());
                
                // For update
                ps.setString(9, account.getAccountName());
                ps.setString(10, account.getPassword());
                ps.setString(11,  account.getSalt());
                ps.setTimestamp(12, new Timestamp(account.getCreateTime().getTime()));
                ps.setInt(13, account.getStatus());
                ps.setString(14, account.getToken());
                ps.setString(15, account.getClientType());
            }
        };
        return jdbcTemplate.update(UPDATE_OR_INSERT, pss);
    }

    @Override
    public List<Account> getAccounts(final int offset, final int count) {
    	ResultSetExtractor<List<Account>> rse = new ResultSetExtractor<List<Account>>() {
            @Override
            public List<Account> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            	List<Account> accounts = new ArrayList<Account>();
                while(resultSet.next()) {
                	accounts.add(extractAccount(resultSet));
                }
                return accounts;
            }
        };

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, offset);
                ps.setInt(2, count);
            }
        };
        return jdbcTemplate.query(GET_ALL, pss, rse);
    }

    private static final Account extractAccount(ResultSet rs) {
        Account a = new Account();
        try {
			a.setId(rs.getString("id"));
			a.setAccountName(rs.getString("accountname"));
			a.setPassword(rs.getString("password"));
			a.setSalt(rs.getString("salt"));
			a.setStatus(rs.getInt("status"));
			a.setToken(rs.getString("token"));
			a.setClientType(rs.getString("clienttype"));
			a.setCreateTime(rs.getDate("createtime"));
		} catch (SQLException e) {
			log.error("extractAccount get error:" + e.getMessage());
		}
        return a;
    }
}

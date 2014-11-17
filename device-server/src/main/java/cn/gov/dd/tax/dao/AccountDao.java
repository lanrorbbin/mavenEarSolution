package cn.gov.dd.tax.dao;

import cn.gov.dd.tax.module.Account;

import java.util.List;

/**
 * Created by lan on 10/29/14.
 */
public interface AccountDao {
    Account getAccountByName(String name);
    Account getAccountById(String id);
    int updateAccount(Account account);
    List<Account> getAccounts(int offset, int count);
}

package cn.gov.dd.tax.service;

import java.util.List;

import cn.gov.dd.tax.module.FacetAccount;
import cn.gov.dd.tax.result.Result;

/**
 * Created by lan on 10/29/14.
 */
public interface AccountService {
    Result<FacetAccount> getAccountByName(String name);
    Result<FacetAccount> getAccountById(String id);
    Result<FacetAccount> updateAccount(String json);
    Result<List<FacetAccount>> getAccounts(int offset, int count);
}

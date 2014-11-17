package cn.gov.dd.tax.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import cn.gov.dd.tax.dao.AccountDao;
import cn.gov.dd.tax.module.Account;
import cn.gov.dd.tax.module.FacetAccount;
import cn.gov.dd.tax.result.Result;
import cn.gov.dd.tax.service.AccountService;
import cn.gov.dd.tax.util.GsonHelper;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.JsonSyntaxException;

/**
 * Created by lan on 10/29/14.
 */
@Slf4j
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	@Override
	public Result<FacetAccount> getAccountByName(String name) {
		Result<FacetAccount> r = new Result<FacetAccount>();
		try {
			Account account = accountDao.getAccountByName(name);
			if (account == null) {
				r.setHttpStatus(HttpStatus.NOT_FOUND);
			} else {
				r.setObj(new FacetAccount(account));
				r.setSuccessCount(1);
			}
		} catch (Exception e) {
			log.error("getAccountByName get error:" + e.getMessage());
			r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return r;
	}

	@Override
	public Result<FacetAccount> updateAccount(String json) {
		Result<FacetAccount> r = new Result<FacetAccount>();
		try {
			Account account = GsonHelper.fromJson(json, Account.class);
			if (account != null) {
				if (StringUtils.isEmpty(account.getId())) {
					r.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
					r.setStatus("id is required");
				} else if (StringUtils.isEmpty(account.getToken())) {
					r.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
					r.setStatus("token is required");
				} else if (StringUtils.isEmpty(account.getClientType())) {
					r.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
					r.setStatus("clientType is required");
				} else {
					account.setCreateTime(new Date());
					if(StringUtils.isEmpty(account.getAccountName())) {
						account.setAccountName(account.getId());
					}
					int ret = accountDao.updateAccount(account);
					if (ret > 0) {
						r.setObj(new FacetAccount(account));
						r.setSuccessCount(ret);
					} else {
						r.setHttpStatus(HttpStatus.NOT_MODIFIED);
						r.setFailedCount(1);
						r.setStatus("Failed");
					}
				}
			} else {
				r.setHttpStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (JsonSyntaxException jse) {
			log.error("parse json error:" + jse.getMessage());
			r.setHttpStatus(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			log.error("Internal error:" + e.getMessage());
			r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return r;
	}

	@Override
	public Result<List<FacetAccount>> getAccounts(int offset, int count) {
		Result<List<FacetAccount>> r = new Result<List<FacetAccount>>();
		if (offset < 0 || count < 1) {
			log.warn("offset or count is invalid, empty list returned.");
			r.setHttpStatus(HttpStatus.BAD_REQUEST);
			List<FacetAccount> list = Collections.emptyList();
			r.setObj(list);
			r.setStatus("offset = " + offset + " or count = " + count
					+ " is invalid");
		} else {
			try {
				List<Account> accounts = accountDao.getAccounts(offset, count);
				if (accounts == null || accounts.size() < 1) {
					r.setStatus("No result");
					r.setHttpStatus(HttpStatus.NO_CONTENT);
				} else {
					List<FacetAccount> fas = Lists.transform(accounts, new Function<Account, FacetAccount>() {
						@Override
						public FacetAccount apply(Account t) {
							return new FacetAccount(t);
						}
					});
					r.setObj(fas);
					r.setSuccessCount(accounts.size());
				}
			} catch (Exception e) {
				log.error("getAccounts with offset = " + offset
						+ " or count = " + count + " failed with "
						+ e.getMessage());
			}
		}
		return r;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Result<FacetAccount> getAccountById(String id) {
		Result<FacetAccount> r = new Result<FacetAccount>();
		try {
			Account account = accountDao.getAccountById(id);
			if (account == null) {
				r.setHttpStatus(HttpStatus.NOT_FOUND);
			} else {
				r.setObj(new FacetAccount(account));
				r.setSuccessCount(1);
			}
		} catch (Exception e) {
			log.error("getAccountById get error:" + e.getMessage());
			r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return r;
	}
}

package cn.gov.dd.tax.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestHeader;

import cn.gov.dd.tax.module.FacetAccount;
import cn.gov.dd.tax.result.Result;
import cn.gov.dd.tax.service.AccountService;

/**
 * Created by lan on 10/29/14.
*/
 @Controller
 @RequestMapping("/v1/account")
 @Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result<FacetAccount>> getAccountByName(@PathVariable String name) {
        log.info("getAccountByName with " + name);
        Result<FacetAccount> result = accountService.getAccountByName(name);
        return new ResponseEntity<Result<FacetAccount>>(result, result.getHttpStatus());
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result<FacetAccount>> getAccountById(@PathVariable String id) {
        log.info("getAccountById with " + id);
        Result<FacetAccount> result = accountService.getAccountById(id);
        return new ResponseEntity<Result<FacetAccount>>(result, result.getHttpStatus());
    }
    /**
    @RequestMapping(value = "/all", method = GET)
    @ResponseBody
    public ResponseEntity<List<Account>> getAllAccounts(@RequestParam int offset, @RequestParam int count) {
        log.info("getAllAccounts with offset:" + offset +", count:" + count);
        HttpStatus status = HttpStatus.OK;
        List<Account> accounts = accountService.getAccounts(offset, count);
        if(accounts == null || accounts.size() < 1) {
            status = HttpStatus.NOT_FOUND;
        }
        log.info("getAccountByName returns " + accounts);
        return new ResponseEntity<List<Account>>(accounts, status);
    }
    */
    
    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Result<FacetAccount>> updateAccount(@RequestBody String json) {
    	log.debug("updateAccount with json:" + json);
    	Result<FacetAccount> result = accountService.updateAccount(json);
    	return new ResponseEntity<Result<FacetAccount>>(result, result.getHttpStatus());
    }
}

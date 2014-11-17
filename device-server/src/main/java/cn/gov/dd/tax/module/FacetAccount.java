package cn.gov.dd.tax.module;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacetAccount {
	protected String id;
    protected String accountName;
    protected Date createTime;
    protected int status;
    protected String clientType;
    protected String token;
    
    public FacetAccount(Account a) {
    	id = a.id;
    	accountName = a.accountName;
    	createTime = a.createTime;
    	status = a.status;
    	clientType = a.clientType;
    	token = a.token;
    }
}

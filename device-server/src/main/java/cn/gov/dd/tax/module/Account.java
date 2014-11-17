package cn.gov.dd.tax.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    protected String id;
    protected String accountName;
    protected String password;
    protected String salt;
    protected Date createTime;
    protected int status;
    protected String clientType;
    protected String token;
}

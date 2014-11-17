package cn.gov.dd.tax.sql;

/**
 * Created by Huanze.Lan on 10/30/2014.
 */
public interface MySQL {
    public static interface Account {
        String BY_NAME = "SELECT id, accountname, password, salt, createtime, status," +
                "token, clienttype FROM accounts WHERE accountname = ?";

        String BY_ID = "SELECT id, accountname, password, salt, createtime, status," +
                "token, clienttype FROM accounts WHERE id = ? ";

        String UPDATE_OR_INSERT = "INSERT INTO accounts (id, accountname, password, salt, createtime, status, token, clienttype) VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?)  ON DUPLICATE KEY UPDATE accountname = ?, password = ?, salt = ?, createtime = ?, status = ?, token = ?, clienttype = ?";

        String GET_ALL = "SELECT id, accountname, password, salt, createtime, status,token, clienttype FROM accounts LIMIT ?,?";
    }
}

package web.test.Connection;

import org.jdbi.v3.core.Jdbi;

public class JDBIConnection {

    String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7726134";

    private String user = "sql7726134";
    private String password = "ZVQ62nfg5j";
    public Jdbi connect() {
        Jdbi jdbi = Jdbi.create(url, user, password);
        return jdbi;
    }
}

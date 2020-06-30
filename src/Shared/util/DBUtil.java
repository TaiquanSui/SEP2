package Shared.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private String dbUrl="jdbc:mysql://localhost:3306/schema?user=root&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT%2B8";
    private String dbUserName="root";
    private String dbPassword="stq20000409";
    private String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";

    public Connection getCon()throws Exception{
        Class.forName(JDBC_DRIVER);
        Connection con= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }

    public void closeCon(Connection con)throws Exception{
        if(con!=null) {
            con.close();
        }
    }


}

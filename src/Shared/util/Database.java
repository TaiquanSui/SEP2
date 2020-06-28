package Shared.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private String dbUrl="jdbc:mysql://localhost:3305/db_sep2?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT%2B8";
    private String dbUserName="root";
    private String dbPassword="123456";
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

    public static void main(String[] args) {
        Database dbUtil=new Database();
        try {
            dbUtil.getCon();
            System.out.println("success");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("fail");
        }
    }
}

package Shared.util;

import Shared.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUser {


    public int add(Connection con, User user)throws Exception {
        String sql="insert into user value(null,?,?,null)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getPassword());
        return pstmt.executeUpdate();
    }


    public User login(Connection con,String email)throws Exception{
        User resultUser=null;
        //try {
        String sql="select * from user where email=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultUser=new User();
            resultUser.setId(rs.getString("id"));
            resultUser.setEmail(rs.getString("email"));
            resultUser.setPassword(rs.getString("password"));
        }
        //}catch(Exception e) {
        //	e.printStackTrace();
        //}
        return resultUser;
    }

    public ResultSet listById(Connection con, User user,String id) throws Exception//, RemoteException
    {
        String sql="select * from user where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, id);

        return pstmt.executeQuery();

    }

    public int delete(Connection con, String id) throws Exception{
        String sql="delete from user where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,id);
        return pstmt.executeUpdate();
    }

    public ResultSet list(Connection con, User user) throws Exception//, RemoteException
    {
        String sql="select * from user";
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    public static ResultSet userInfo(Connection con, User user) throws Exception//, RemoteException
    {
        String sql="select id from user where email=666";
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    public static ResultSet getID(Connection con, User user) throws Exception//, RemoteException
    {
        String sql="select id from user where email=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getEmail());
        return pstmt.executeQuery();
    }

    public int update(Connection con,  User user) throws Exception{
        String sql="update user set id=? ,email=? ,password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getEmail());
        pstmt.setString(3, user.getPassword());
        return pstmt.executeUpdate();
    }
}

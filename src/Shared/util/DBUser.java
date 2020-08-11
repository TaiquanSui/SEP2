package Shared.util;

import Shared.Model.Product;
import Shared.Model.User;
import Shared.Model.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUser {


    public int add(Connection con, User user)throws Exception {
        String sql="insert into user value(null,?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getType().toString());
        return pstmt.executeUpdate();
    }


    public int changePassword(Connection con, String email, String password) throws Exception{
        String sql="update user set password = ? where email=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, email);

        return pstmt.executeUpdate();
    }



    public User getUser(Connection con, String email)throws Exception{
        User resultUser=null;

        String sql="select * from user where email=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()) {
            resultUser=new User();
            resultUser.setId(rs.getString("id"));
            resultUser.setEmail(rs.getString("email"));
            resultUser.setPassword(rs.getString("password"));
            if(rs.getString("type").equals(UserType.Administrator.toString())){
                resultUser.setType(UserType.Administrator);
            }else if(rs.getString("type").equals(UserType.Customer.toString())){
                resultUser.setType(UserType.Customer);
            }

        }

        return resultUser;
    }



    public ResultSet getAllCustomers(Connection con) throws Exception//, RemoteException
    {
        String sql="select * from user where type = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, UserType.Customer.toString());

        return pstmt.executeQuery();
    }

    public ResultSet getSearchResultOfCustomers(Connection con, String searchText) throws Exception//, RemoteException
    {
        String sql="select * from user where email like ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, "%"+searchText+"%");
        return pstmt.executeQuery();
    }


}

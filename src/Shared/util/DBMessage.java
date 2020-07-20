package Shared.util;

import Shared.Model.Message;
import Shared.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBMessage {


    public int saveMessage(Connection con, Message message)throws Exception {
        String sql="insert into user value(?,?,?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, message.getText());
        pstmt.setString(2, message.getDate());
        pstmt.setString(3, message.getSenderEmail());
        pstmt.setString(4, message.getReceiverEmail());
        pstmt.setBoolean(5,message.isRead() );

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

    public ResultSet getProductList(Connection con, String name) throws Exception//, RemoteException
    {
//		StringBuffer sb=new StringBuffer("select * from product");
////		PreparedStatement pstmt=con.prepareStatement(sql);
////		pstmt.setString(1, product.getSeller());
//		//pstmt.setString(2, user.getEmail());
//
//		if(StringUtil.isNotEmpty(product.getName())) {
//			sb.append(" and name like '%"+product.getName()+"%'");
//		}
////		if(product.getPrice() !=0) {
////			sb.append(" where price like '%"+product.getPrice()+"%'");
////		}
////		if(StringUtil.isNotEmpty(product.getDetail())) {
////			sb.append(" where detail like '%"+product.getDetail()+"%'");
////		}
//		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//		return pstmt.executeQuery();
        String sql="select * from product where name like ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, "%"+name+"%");
        return pstmt.executeQuery();
    }


}

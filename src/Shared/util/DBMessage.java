package Shared.util;

import Shared.Model.Message;
import Shared.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBMessage {

    public int saveMessage(Connection con, Message message)throws Exception {
        String sql="insert into message value(null,?,?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, message.getText());
        pstmt.setString(2, message.getDate());
        pstmt.setString(3, message.getSenderEmail());
        pstmt.setString(4, message.getReceiverEmail());

        return pstmt.executeUpdate();
    }

    public ResultSet getNumOfMessages(Connection con, String email) throws Exception{
        String sql = "select count(*) as row_count from message where receiverEmail = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, email);

        return pstmt.executeQuery();
    }

    public ResultSet getMessages(Connection con, String email) throws Exception
    {
        String getSql = "select * from message where receiverEmail = ?";
        PreparedStatement pstmt1=con.prepareStatement(getSql);
        pstmt1.setString(1, email);
        ResultSet resultSet =  pstmt1.executeQuery();

        String deleteSql = "delete from message where receiverEmail = ?";
        PreparedStatement pstmt2 = con.prepareStatement(deleteSql);
        pstmt2.setString(1, email);
        pstmt2.executeUpdate();

        return resultSet;
    }



}

package Shared.util;

import Shared.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBProduct {


    public int add(Connection con, Product product)throws Exception//, RemoteException
    {
        String sql="insert into product value(null,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, product.getName());
        pstmt.setDouble(2, product.getPrice());
        pstmt.setString(3, product.getDescription());
        pstmt.setString(4, product.getSeller());
        return pstmt.executeUpdate();
    }

    public ResultSet getProductList(Connection con, String name) throws Exception//, RemoteException
    {
        String sql="select * from product where name like ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, "%"+name+"%");
        return pstmt.executeQuery();
    }

    public ResultSet getAllProductsOnSale(Connection con, String seller) throws Exception{
        String sql="select * from product where seller = ?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, seller);
        return pstmt.executeQuery();
    }


    public int delete(Connection con, String id) throws Exception{
        String sql="delete from product where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,id);
        return pstmt.executeUpdate();
    }

    public int update(Connection con, Product product) throws Exception{
        String sql="update product set name=? ,price=? ,description=? ,seller=? where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, product.getName());
        pstmt.setDouble(2, product.getPrice());
        pstmt.setString(3, product.getDescription());
        pstmt.setString(4, product.getSeller());
        pstmt.setInt(5,product.getId());
        return pstmt.executeUpdate();
    }


}

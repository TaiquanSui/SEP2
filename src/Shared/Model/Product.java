package Shared.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private String detail;
    private String seller;
    public Product(int id, String name,double price,String detail) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.detail=detail;
    }
    public Product(String name,double price,String detail,String seller) {
        this.name=name;
        this.price=price;
        this.seller=seller;
        this.detail=detail;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }




}

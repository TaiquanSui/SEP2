package Shared.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private String description;
    private String seller;

    public Product(int id, String name,double price,String description) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.description = description;
    }

    public Product(String name, double price, String description, String seller) {
        this.name=name;
        this.price=price;
        this.seller=seller;
        this.description = description;
    }

    public Product(int id, String name, double price, String description, String seller) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.description = description;
        this.seller=seller;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }


}

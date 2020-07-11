package Server;

import Client.Networking.IClient;
import Shared.Model.Message;
import Shared.Model.Product;
import Shared.Model.User;
import Shared.util.DBProduct;
import Shared.util.DBUser;
import Shared.util.DBUtil;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements IServer{

    private List<ClientContainer> clients;

    private DBUtil dbUtil;
    private DBUser dbUser;
    private DBProduct dbProduct;


    public ServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        clients = new ArrayList<ClientContainer>();

        dbUtil = new DBUtil();
        dbUser = new DBUser();
        dbProduct = new DBProduct();
    }


    @Override
    public boolean registerClient(IClient userClient, String email) throws RemoteException {
        for (ClientContainer client : clients) {
            if(client.id.equals(email)) return false;
        }

        clients.add(new ClientContainer(email, userClient));

        return true;
    }

    @Override
    public boolean createNewUser(User user) throws RemoteException {
        Connection con =null;

        boolean createUser = false;

        try {
            con = dbUtil.getCon();

            int addUser = dbUser.add(con, user);
            if(addUser==1){
                createUser = true;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return createUser;
    }

    @Override
    public void changePassword(String Id, String newPw) throws RemoteException {

    }

    @Override
    public User getUser(String email) throws RemoteException {
        Connection con =null;

        try {
            con= dbUtil.getCon();
            User currentUser=dbUser.login(con, email);

            return currentUser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public void sendMessage(String msg, String id) throws RemoteException {
        for (ClientContainer client : clients) {
            if(client.id.equals(id)) continue;

            try {
                client.client.receiveMessage(msg, id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Message> getMessages(String clientID) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Product> getSearchResult(String searchText) throws RemoteException {
        Connection con =null;

        try {
            con= dbUtil.getCon();
            ResultSet rs=dbProduct.getProductList(con,searchText);
            ArrayList<Product> products = new ArrayList<Product>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = Double.parseDouble(rs.getString("price"));
                String description = rs.getString("description");
                String seller = rs.getString("seller");


                Product product = new Product(id, name, price, description, seller);
                products.add(product);
            }

            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Product> getAllProductsOnSale(String email) throws RemoteException {
        Connection con =null;

        try {
            con= dbUtil.getCon();
            ResultSet rs=dbProduct.getAllProductsOnSale(con,email);
            ArrayList<Product> products = new ArrayList<Product>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = Double.parseDouble(rs.getString("price"));
                String description = rs.getString("description");
                String seller = rs.getString("seller");

                Product product = new Product(id, name, price, description, seller);
                products.add(product);
            }

            return products;

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        System.out.println("null");
        return null;
    }


    @Override
    public boolean addProduct(Product product) throws RemoteException {
        Connection con =null;

        try {
            con=dbUtil.getCon();

            //int currentProduct=remoteProduct.add(con, product);
            int currentProduct=dbProduct.add(con, product);

            if(currentProduct==1) {
                return true;
            }else {
                return false;
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean editProduct(Product product) {
        Connection con =null;

        try {
            con=dbUtil.getCon();

            //int currentProduct=remoteProduct.add(con, product);
            int currentProduct=dbProduct.update(con, product);

            if(currentProduct==1) {
                return true;
            }else {
                return false;
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteProduct(String id) throws RemoteException {
        Connection con =null;

        try {
            con=dbUtil.getCon();

            //int currentProduct=remoteProduct.add(con, product);
            int currentProduct=dbProduct.delete(con, id);

            if(currentProduct==1) {
                return true;
            }else {
                return false;
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public void logout(String id) {
        IClient toRemove = null;
        for (ClientContainer client : clients) {
            if(client.id.equals(id)) {
                toRemove = client.client;
            }
        }
        clients.remove(toRemove);
    }








    private class ClientContainer {
        String id;
        IClient client;

        ClientContainer(String id, IClient client) {
            this.id = id;
            this.client = client;
        }
    }










}

package Server;

import Client.Networking.IClient;
import Shared.Model.*;
import Shared.util.DBMessage;
import Shared.util.DBProduct;
import Shared.util.DBUser;
import Shared.util.DBUtil;

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
    private DBMessage dbMessage;


    public ServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        clients = new ArrayList<ClientContainer>();

        dbUtil = new DBUtil();
        dbUser = new DBUser();
        dbProduct = new DBProduct();
        dbMessage = new DBMessage();
    }


    @Override
    public boolean registerClient(IClient userClient, String email) throws RemoteException {
        for (ClientContainer client : clients) {
            if(client.email.equals(email)) return false;
        }

        clients.add(new ClientContainer(email, userClient));

        for(ClientContainer client : clients){
            System.out.println(client.email);
        }
        return true;
    }


    @Override
    public void logout(String email) {
        ClientContainer toRemove = null;

        for (ClientContainer client : clients) {
            if(client.email.equals(email)) {
                toRemove = client;
                System.out.println("server log out");
            }
        }

        if(toRemove!=null){
            clients.remove(toRemove);
        }

    }


    @Override
    public boolean createNewUser(User user) throws RemoteException {
        Connection con = null;

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
    public boolean changePassword(String email, String newPW) throws RemoteException {

        Connection con = null;

        try {
            con = dbUtil.getCon();

            int resultToChangePassword = dbUser.changePassword(con, email, newPW);

            if(resultToChangePassword==1) {
                return true;
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public User getUser(String email) throws RemoteException {
        Connection con =null;

        try {
            con = dbUtil.getCon();
            User currentUser= dbUser.getUser(con, email);

            return currentUser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public boolean sendMessage(Message message) throws RemoteException {

        for (ClientContainer client : clients) {
            if(client.email.equals(message.getReceiverEmail())) {
                try {
                    client.client.receiveMessage(message);
                    return true;
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }


        Connection con =null;

        try {

            con= dbUtil.getCon();

            int resultToSaveMessage = dbMessage.saveMessage(con,message);

            if(resultToSaveMessage==1) {
                return true;
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public int getNumOfMessages(String email) throws RemoteException {
        Connection con =null;

        try {
            con=dbUtil.getCon();

            //int currentProduct=remoteProduct.add(con, product);
            ResultSet resultSet =dbMessage.getNumOfMessages(con,email);

            int numOfMessages = 0;

            while (resultSet.next()){
                numOfMessages = resultSet.getInt("row_count") ;
            }

            return numOfMessages;
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
        return 0;
    }


    @Override
    public ArrayList<Session> getOfflineMessages(String email) throws RemoteException {
        Connection con =null;

        try {
            con= dbUtil.getCon();
            ResultSet rs = dbMessage.getMessages(con,email);
            ArrayList<Message> messages = new ArrayList<Message>();

            while (rs.next()) {
                String text = rs.getString("text");
                String date = rs.getString("date");
                String senderEmail = rs.getString("senderEmail");
                String receiverEmail = rs.getString("receiverEmail");

                Message message = new Message(text, date, senderEmail, receiverEmail);
                messages.add(message);
            }


            ArrayList<Session> sessions = new ArrayList<Session>();

            boolean senderExists = false;

            for (Message message : messages){

                for(Session session : sessions){
                    if(session.getSenderEmail().equals(message.getSenderEmail())){
                        session.addMessage(message);
                        senderExists = true;
                    }
                }

                if (!senderExists){
                    Session session = new Session(message.getSenderEmail());
                    session.addMessage(message);
                    sessions.add(session);
                }

                senderExists = false;
            }

            return sessions;

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
        return null;
    }


    @Override
    public ArrayList<Product> getSearchResult(String searchText) throws RemoteException {
        Connection con = null;

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
    public ArrayList<User> getAllCustomers() throws RemoteException {
        Connection con =null;

        try {
            con= dbUtil.getCon();
            ResultSet rs=dbUser.getAllCustomers(con);
            ArrayList<User> users = new ArrayList<User>();

            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");

                User user = new User(email,password,UserType.Customer);
                users.add(user);
            }

            return users;

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

        return null;
    }

    @Override
    public ArrayList<User> getSearchResultOfCustomers(String searchText) throws RemoteException {
        Connection con =null;

        try {
            con= dbUtil.getCon();
            ResultSet rs=dbUser.getSearchResultOfCustomers(con,searchText);
            ArrayList<User> users = new ArrayList<User>();

            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");

                User user = new User(email,password,UserType.Customer);
                users.add(user);
            }

            return users;

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

        return null;
    }


    @Override
    public String deleteUser(String id) throws RemoteException {
        return null;
    }




    @Override
    public String getUserStatus(String email) throws RemoteException {
        for (ClientContainer client : clients) {
            if(client.email.equals(email)) {
                return "online";
            }
        }
        return "offline";
    }


    private class ClientContainer {
        String email;
        IClient client;

        ClientContainer(String email, IClient client) {
            this.email = email;
            this.client = client;
        }
    }










}

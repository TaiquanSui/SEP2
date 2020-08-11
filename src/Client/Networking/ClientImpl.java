package Client.Networking;

import Client.View.CustomerService.ChatViewController;
import Server.IServer;
import Shared.Model.Message;
import Shared.Model.Product;
import Shared.Model.Session;
import Shared.Model.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ClientImpl implements IClient{

    private IServer server;

    private String emailOfUserLogin;
    private ArrayList<Product> productsOnSale;

    private ChatViewController chatViewController;


    public ClientImpl() throws RemoteException, NotBoundException
    {
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 8080);
        server = (IServer)registry.lookup("Server");
    }

    @Override
    public void registerClient(String email) throws RemoteException {
        server.registerClient(this,email);
    }

    @Override
    public void logout() throws RemoteException {
        server.logout(emailOfUserLogin);
        System.out.println("client log out");
    }

    @Override
    public String getEmailOfUserLogin() throws RemoteException {
        return emailOfUserLogin;
    }

    @Override
    public void setEmailOfUserLogin(String emailOfUserLogin) throws RemoteException {
        this.emailOfUserLogin = emailOfUserLogin;
    }

    @Override
    public void setChatView(ChatViewController chatViewController) throws RemoteException {
        this.chatViewController = chatViewController;
    }

    @Override
    public String getUserStatus(String email) throws RemoteException {
        return server.getUserStatus(email);
    }

    @Override
    public boolean sendMessage(Message message) throws RemoteException {
        return server.sendMessage(message);
    }

    @Override
    public void receiveMessage(Message message) throws RemoteException {
        chatViewController.receiveMessage(message);
    }

    @Override
    public int getNumOfMessages() throws RemoteException {
        return server.getNumOfMessages(emailOfUserLogin);
    }

    @Override
    public ArrayList<Session> getOfflineMessages() throws RemoteException {
        return server.getOfflineMessages(emailOfUserLogin);
    }

    @Override
    public boolean createNewUser(User user) throws RemoteException {
        return server.createNewUser(user);
    }

    @Override
    public User getUser(String email) throws RemoteException {
        return server.getUser(email);
    }

    @Override
    public boolean changePassword(String newPw) throws RemoteException {
        return server.changePassword(emailOfUserLogin,newPw);
    }

    @Override
    public ArrayList<Product> getProductList(String searchText) throws RemoteException {
        return server.getSearchResult(searchText);
    }

    @Override
    public ArrayList<Product> getAllProductsOnSale() throws RemoteException {
        productsOnSale = server.getAllProductsOnSale(emailOfUserLogin);
        return productsOnSale;
    }

    @Override
    public ArrayList<Product> getSearchResultOfSeller(String searchText) throws RemoteException {

        ArrayList<Product> searchResult = new ArrayList<Product>();

        for (Product product : productsOnSale) {
            if (product.getName().contains(searchText)) {
                searchResult.add(product);
            }
        }

        return searchResult;
    }

    @Override
    public boolean addProduct(Product product) throws RemoteException {
        return server.addProduct(product);
    }

    @Override
    public boolean editProduct(Product product) throws RemoteException{
        return server.editProduct(product);
    }

    @Override
    public boolean deleteProduct(String id) throws RemoteException{
        return server.deleteProduct(id);
    }

    @Override
    public ArrayList<User> getAllCustomers() throws RemoteException {
        return server.getAllCustomers();
    }

    @Override
    public ArrayList<User> getSearchResultOfCustomers(String searchText) throws RemoteException {
        return server.getSearchResultOfCustomers(searchText);
    }

    @Override
    public String deleteUser(String id) throws RemoteException {
        return server.deleteUser(id);
    }


}

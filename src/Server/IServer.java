package Server;

import Client.Networking.IClient;
import Shared.Model.Message;
import Shared.Model.Product;
import Shared.Model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServer extends Remote {
    boolean registerClient(IClient client, String email) throws RemoteException;

    boolean createNewUser(User user) throws RemoteException;
    void changePassword(String Id, String newPw) throws RemoteException;
    User getUser(String CustomerId) throws RemoteException;
    void logout(String name) throws RemoteException;

    void sendMessage(String msg, String name) throws RemoteException;
    ArrayList<Message> getMessages(String clientID) throws RemoteException;
    
    ArrayList<Product> getProductList(String searchText) throws RemoteException;
    boolean addProduct(Product product) throws RemoteException;
    boolean editProduct(Product product) throws RemoteException;
}

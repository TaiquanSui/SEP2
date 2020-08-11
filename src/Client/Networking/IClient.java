package Client.Networking;

import Client.View.CustomerService.ChatViewController;
import Shared.Model.Message;
import Shared.Model.Product;
import Shared.Model.Session;
import Shared.Model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClient extends Remote {
    void registerClient(String email) throws RemoteException;
    void logout() throws RemoteException;
    String getEmailOfUserLogin() throws RemoteException;
    void setEmailOfUserLogin(String emailOfUserLogin) throws RemoteException;

    void setChatView(ChatViewController chatViewController) throws RemoteException;
    String getUserStatus(String email) throws RemoteException;
    boolean sendMessage(Message message) throws RemoteException;
    void receiveMessage(Message message) throws RemoteException;
    int getNumOfMessages() throws RemoteException;
    ArrayList<Session> getOfflineMessages() throws RemoteException;

    boolean createNewUser(User user) throws RemoteException;
    User getUser(String email) throws RemoteException;
    boolean changePassword(String newPw) throws RemoteException;

    ArrayList<Product> getProductList(String searchText) throws RemoteException;

    ArrayList<Product> getAllProductsOnSale() throws RemoteException;
    ArrayList<Product> getSearchResultOfSeller(String searchText) throws RemoteException;
    boolean addProduct(Product product) throws RemoteException;
    boolean editProduct(Product product) throws RemoteException;
    boolean deleteProduct(String id) throws RemoteException;

    ArrayList<User> getAllCustomers() throws RemoteException;
    ArrayList<User> getSearchResultOfCustomers(String searchText) throws RemoteException;
    String deleteUser(String id) throws RemoteException;


}

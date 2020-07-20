package Client.Networking;

import Client.View.UserService.ChatViewController;
import Shared.Model.Message;
import Shared.Model.Product;
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
    boolean sendMessageToOnlineUser(Message message) throws RemoteException;
    boolean sendMessageToOfflineUser(Message message) throws RemoteException;
    void receiveMessage(Message message) throws RemoteException;

    boolean createNewUser(User user) throws RemoteException;
    User getUser(String email) throws RemoteException;
    void changePassword(String Id, String newPw) throws RemoteException;

    ArrayList<Product> getProductList(String searchText) throws RemoteException;

    ArrayList<Product> getAllProductsOnSale() throws RemoteException;
    ArrayList<Product> getSearchResultOfSeller(String searchText) throws RemoteException;
    boolean addProduct(Product product) throws RemoteException;
    boolean editProduct(Product product) throws RemoteException;
    boolean deleteProduct(String id) throws RemoteException;



}

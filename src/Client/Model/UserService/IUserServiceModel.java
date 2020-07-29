package Client.Model.UserService;

import Client.View.CustomerService.ChatViewController;
import Shared.Model.Message;
import Shared.Model.Product;
import Shared.Model.Session;
import Shared.Model.User;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IUserServiceModel {

    String getEmail() throws RemoteException;

    ArrayList<Product> getSearchResult(String name) throws RemoteException;

    String addProduct(String name, double price, String detail) throws RemoteException;
    String editProduct(int id, String name, double price, String detail) throws RemoteException;
    String deleteProduct(String id) throws RemoteException;

    ArrayList<Product> getAllProductsOnSale() throws RemoteException;
    ArrayList<Product> getSearchResultOfSeller(String searchText) throws RemoteException;

    void addChatViewToClient(ChatViewController chatViewController) throws RemoteException;
    boolean sendMessage(Message message) throws RemoteException;
    int getNumOfMessages() throws RemoteException;
    ArrayList<Session> getOfflineMessages() throws RemoteException;

    ArrayList<User> getAllCustomers() throws RemoteException;
    ArrayList<User> getSearchResultOfCustomers(String searchText) throws RemoteException;
    String deleteUser(String id) throws RemoteException;

}

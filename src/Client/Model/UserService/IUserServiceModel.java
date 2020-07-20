package Client.Model.UserService;

import Client.View.UserService.ChatViewController;
import Shared.Model.Message;
import Shared.Model.Product;

import java.beans.PropertyChangeListener;
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
    boolean sendMessageToOnlineUser(Message message) throws RemoteException;
    boolean sendMessageToOfflineUser(Message message) throws RemoteException;
    int getNumOfMessages(String email) throws RemoteException;
}

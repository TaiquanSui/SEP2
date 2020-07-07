package Client.Networking;

import Shared.Model.Product;
import Shared.Model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClient extends Remote {
    void registerClient(String email) throws RemoteException;
    String getEmailOfUserLogin() throws RemoteException;
    void setEmailOfUserLogin(String emailOfUserLogin) throws RemoteException;

    void sendMessage(String msg, String email) throws RemoteException;
    void receiveMessage(String msg, String email) throws RemoteException;

    boolean createNewUser(User user) throws RemoteException;
    User getUser(String email) throws RemoteException;
    void changePassword(String Id, String newPw) throws RemoteException;

    ArrayList<Product> getProductList(String searchText) throws RemoteException;
    boolean addProduct(Product product) throws RemoteException;
    boolean editProduct(Product product) throws RemoteException;
}

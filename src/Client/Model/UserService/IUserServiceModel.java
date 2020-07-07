package Client.Model.UserService;

import Shared.Model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IUserServiceModel {

    ArrayList<Product> getProductList(String name) throws RemoteException;
    String addProduct(String name, double price, String detail) throws RemoteException;
    int getNumOfMessages(String email) throws RemoteException;
    String editProduct(int id, String name, double price, String detail) throws RemoteException;
}

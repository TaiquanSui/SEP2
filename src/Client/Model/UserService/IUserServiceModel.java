package Client.Model.UserService;

import Shared.Model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IUserServiceModel {

    ArrayList<Product> getSearchResult(String name) throws RemoteException;

    String addProduct(String name, double price, String detail) throws RemoteException;
    String editProduct(int id, String name, double price, String detail) throws RemoteException;
    String deleteProduct(String id) throws RemoteException;


    ArrayList<Product> getAllProductsOnSale() throws RemoteException;
    ArrayList<Product> getSearchResultOfSeller(String searchText) throws RemoteException;

    int getNumOfMessages(String email) throws RemoteException;
}

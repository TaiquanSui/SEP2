package Client.Model.UserService;

import Client.Networking.IClient;
import Shared.Model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserServiceImpl implements IUserServiceModel {

    private IClient client;

    public UserServiceImpl(IClient client) {
        this.client = client;
    }

    @Override
    public ArrayList<Product> getSearchResult(String searchText) throws RemoteException {
        return client.getProductList(searchText);
    }

    @Override
    public String addProduct(String name, double price, String description) throws RemoteException {
        String seller = client.getEmailOfUserLogin();
        Product product = new Product(name,price,description,seller);

        boolean result = client.addProduct(product);

        if(result){
            return "OK";
        }else {
            return "Server failed";
        }
    }

    @Override
    public String editProduct(int id, String name, double price, String detail) throws RemoteException {
        String seller = client.getEmailOfUserLogin();
        Product product = new Product(id,name,price,detail,seller);

        boolean result = client.editProduct(product);

        if(result){
            return "OK";
        }else {
            return "Server failed";
        }
    }

    @Override
    public String deleteProduct(String id) throws RemoteException {

        boolean result = client.deleteProduct(id);

        if(result){
            return "OK";
        }else {
            return "Server failed";
        }
    }

    @Override
    public ArrayList<Product> getSearchResultOfSeller(String searchText) throws RemoteException {
        return client.getSearchResultOfSeller(searchText);
    }

    @Override
    public ArrayList<Product> getAllProductsOnSale() throws RemoteException {
        return client.getAllProductsOnSale();
    }

    @Override
    public int getNumOfMessages(String email) throws RemoteException {
        return 0;
    }


}

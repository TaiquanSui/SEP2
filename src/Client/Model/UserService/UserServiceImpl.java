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
    public ArrayList<Product> getProductList(String searchText) throws RemoteException {
        return client.getProductList(searchText);
    }





}

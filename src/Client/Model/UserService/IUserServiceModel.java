package Client.Model.UserService;

import Shared.Model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IUserServiceModel {

    ArrayList<Product> getProductList(String name) throws RemoteException;

}

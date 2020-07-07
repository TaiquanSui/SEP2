package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Shared.Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SellerOverviewVM {
    private final IUserServiceModel userServiceModel;
    private StringProperty searchProperty = new SimpleStringProperty(); // holds the username from the GUI textfield

    public SellerOverviewVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public ArrayList<Product> getSellerProductList() throws RemoteException {
        return userServiceModel.getProductList(searchProperty.getValue());
    }

    public StringProperty searchProperty() {
        return searchProperty;
    }
}

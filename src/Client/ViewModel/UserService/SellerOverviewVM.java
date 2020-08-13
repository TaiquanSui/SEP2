package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Shared.Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SellerOverviewVM {
    private final IUserServiceModel userServiceModel;
    private StringProperty searchText = new SimpleStringProperty(); // holds the username from the GUI textfield

    public SellerOverviewVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public ArrayList<Product> getAllProductsOnSale() throws RemoteException {
        return userServiceModel.getAllProductsOnSale();
    }

    public ArrayList<Product> getSearchResultOfSeller() throws RemoteException {
        return userServiceModel.getSearchResultOfSeller(searchText.getValue());
    }

    public String deleteProduct(String id) throws RemoteException {
        return userServiceModel.deleteProduct(id);
    }

    public void logout() throws RemoteException{
        userServiceModel.logout();
    }



    public void clearFields() {
        searchText.setValue("");
    }



    public StringProperty searchProperty() {
        return searchText;
    }

}

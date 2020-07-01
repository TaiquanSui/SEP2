package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Shared.Model.Product;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SearchProductVM {
    private final IUserServiceModel iUserServiceModel; // reference to the Model interface
    private StringProperty searchProperty = new SimpleStringProperty(); // holds the username from the GUI textfield


    public SearchProductVM(IUserServiceModel iUserServiceModel) {
        this.iUserServiceModel = iUserServiceModel;
    }


    public ArrayList<Product> getProductList() throws RemoteException {
        return iUserServiceModel.getProductList(searchProperty.getValue());
    }

    public StringProperty searchProperty() {
        return searchProperty;
    }



}


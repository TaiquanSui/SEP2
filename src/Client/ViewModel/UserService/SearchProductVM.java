package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Shared.Model.Product;
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


    public ArrayList<Product> getSearchResult() throws RemoteException {
        return iUserServiceModel.getSearchResult(searchProperty.getValue());
    }

    public void logout() throws RemoteException{
        iUserServiceModel.logout();
    }

    public StringProperty searchProperty() {
        return searchProperty;
    }



}


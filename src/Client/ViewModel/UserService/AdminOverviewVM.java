package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Shared.Model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdminOverviewVM {

    private final IUserServiceModel userServiceModel;
    private StringProperty searchText = new SimpleStringProperty(); // holds the username from the GUI textfield

    public AdminOverviewVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public ArrayList<User> getAllCustomers() throws RemoteException {
        return userServiceModel.getAllCustomers();
    }

    public String deleteUser(String id) throws RemoteException {
        return userServiceModel.deleteUser(id);
    }



    public void clearFields() {
        searchText.setValue("");
    }



    public StringProperty searchProperty() {
        return searchText;
    }

    public ArrayList<User> getSearchResultOfSeller() throws RemoteException {
        return userServiceModel.getSearchResultOfCustomers(searchText.getValue());
    }
}

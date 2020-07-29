package Client.View.AdminService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.AdminOverviewVM;
import Shared.Model.Product;
import Shared.Model.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdminOverviewController {
    @FXML
    private Menu menu;
    @FXML
    private MenuItem logout;
    @FXML
    private TextField searchText;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;


    private AdminOverviewVM adminOverviewVM;
    private ViewHandler viewHandler;



    public void init(AdminOverviewVM adminOverviewVM, ViewHandler viewHandler) throws RemoteException {
        this.adminOverviewVM = adminOverviewVM;
        this.viewHandler = viewHandler;

        searchText.textProperty().bindBidirectional(adminOverviewVM.searchProperty());

        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory("password"));

        getAllCustomers();
    }


    public void getSearchResultOfUser(ActionEvent actionEvent) throws RemoteException {
        ArrayList<User> searchResult = adminOverviewVM.getSearchResultOfSeller();

        if(searchResult==null){
            JOptionPane.showMessageDialog(null, "No result","Fail", JOptionPane.ERROR_MESSAGE);
        }else {
            ObservableList<User> userList = FXCollections.observableArrayList();

            userList.addAll(searchResult);

            userTableView.setItems(userList);
        }
    }



    public void deleteUser(ActionEvent actionEvent) throws RemoteException {
        User userSelected = userTableView.getSelectionModel().selectedItemProperty().getValue();

        String result = "";

        try{
            String id = String.valueOf(userSelected.getId());
            result = adminOverviewVM.deleteUser(id);
        }catch (Exception e){
            result = "client exception";
        }

        if("OK".equals(result)){
            adminOverviewVM.clearFields();
            //open OverviewController window
            getAllCustomers();
        }else{
            JOptionPane.showMessageDialog(null, result,"Fail", JOptionPane.ERROR_MESSAGE);
        }

    }











    public void getAllCustomers() throws RemoteException {
        /**
         *   get the list of products on sale
         */
        ArrayList<User> userArrayList = adminOverviewVM.getAllCustomers();

        ObservableList<User> userList = FXCollections.observableArrayList();

        userList.addAll(userArrayList);

        userTableView.setItems(userList);
    }


    public void logout(ActionEvent actionEvent) throws RemoteException {


    }

}

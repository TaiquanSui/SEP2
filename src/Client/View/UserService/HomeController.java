package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.Login.CreateUserVM;
import Shared.Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.rmi.RemoteException;

public class HomeController {

    @FXML
    private Menu menu;
    @FXML
    private MenuItem logout;
    @FXML
    private TextField SearchText;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private TableColumn<Product, String> sellerColumn;




    public void init(CreateUserVM createUserVM, ViewHandler viewHandler) {
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        sellerColumn.setCellValueFactory(new PropertyValueFactory("seller"));

    }



    public void logout(ActionEvent actionEvent) throws RemoteException {

        JOptionPane.showMessageDialog(null, "","User create failed", JOptionPane.ERROR_MESSAGE);

    }



}

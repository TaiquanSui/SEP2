package Client.View.CustomerService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.SellerOverviewVM;
import Shared.Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.rmi.RemoteException;

public class ProductDetailController {

    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label seller;
    @FXML
    private Label description;

    private ViewHandler viewHandler;
    private Product product;



    public void init(ViewHandler viewHandler, Product product) throws RemoteException {
        this.viewHandler = viewHandler;
        this.product = product;

        name.textProperty().setValue(product.getName());
        price.textProperty().setValue(String.valueOf(product.getPrice()));
        seller.textProperty().setValue(product.getSeller());
        description.textProperty().setValue(product.getDescription());
    }


    public void onChatButton(ActionEvent actionEvent) throws RemoteException {
        viewHandler.openChatView(product.getSeller());
    }

    public void onBuyButton(ActionEvent actionEvent) throws RemoteException {
        JOptionPane.showMessageDialog(null, "You will redirect to the payment page" ,"Add failed", JOptionPane.INFORMATION_MESSAGE);
    }


    public void onCancelButton(ActionEvent actionEvent) throws RemoteException {
        viewHandler.closeProductDetailView();
    }






}

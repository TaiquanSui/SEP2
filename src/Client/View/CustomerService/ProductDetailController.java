package Client.View.CustomerService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.ProductDetailVM;
import Client.ViewModel.UserService.SellerOverviewVM;
import Shared.Model.Product;
import Shared.Model.Session;
import javafx.application.Platform;
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

    private ProductDetailVM productDetailVM;
    private ViewHandler viewHandler;
    private Product product;


    public void init(ViewHandler viewHandler, ProductDetailVM productDetailVM) throws RemoteException {
        this.viewHandler = viewHandler;
        this.productDetailVM = productDetailVM;

        name.textProperty().bindBidirectional(productDetailVM.nameProperty());
        price.textProperty().bindBidirectional(productDetailVM.priceProperty());
        seller.textProperty().bindBidirectional(productDetailVM.sellerProperty());
        description.textProperty().bindBidirectional(productDetailVM.descriptionProperty());

    }


    public void setValues(Product product) {
        this.product = product;
        productDetailVM.setValues(product);
    }

    public void clearFields(){
        productDetailVM.clearFields();
    }


    public void onChatButton(ActionEvent actionEvent) throws RemoteException {
        viewHandler.openChatView(product.getSeller());
    }

    public void onBuyButton(ActionEvent actionEvent) throws RemoteException {
        JOptionPane.showMessageDialog(null, "You will redirect to the payment page" ,"Buy", JOptionPane.INFORMATION_MESSAGE);
    }


    public void onCancelButton(ActionEvent actionEvent) throws RemoteException {
        viewHandler.closeProductDetailView();
    }



}

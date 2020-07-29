package Client.View.CustomerService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.AddProductVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import javax.swing.*;
import java.rmi.RemoteException;

public class AddProductController {

    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextArea detail;

    private AddProductVM addProductVM;
    private SellerOverviewController sellerOverviewController;
    private ViewHandler viewHandler;

    public void init(AddProductVM addProductVM, SellerOverviewController sellerOverviewController, ViewHandler viewHandler) {
        this.addProductVM = addProductVM;
        this.viewHandler = viewHandler;
        this.sellerOverviewController = sellerOverviewController;

        name.textProperty().bindBidirectional(addProductVM.nameProperty());
        price.textProperty().bindBidirectional(addProductVM.priceProperty());
        detail.textProperty().bindBidirectional(addProductVM.detailProperty());
    }


    public void onAddButton(ActionEvent actionEvent) throws RemoteException {
        String result = addProductVM.addProduct();

        if("OK".equals(result)){
            addProductVM.clearFields();
            //open OverviewController window
            JOptionPane.showMessageDialog(null, "Add successfully",null, JOptionPane.INFORMATION_MESSAGE);
            viewHandler.closeAddProductView();
            sellerOverviewController.getAllProductsOnSale();

        }else{
            JOptionPane.showMessageDialog(null, result,"Add failed", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void onCancelButton(ActionEvent actionEvent) throws RemoteException {
            viewHandler.closeAddProductView();
    }




}

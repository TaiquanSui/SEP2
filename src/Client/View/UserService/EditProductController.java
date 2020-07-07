package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.AddProductVM;
import Client.ViewModel.UserService.EditProductVM;
import Shared.Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;

public class EditProductController {

    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextArea detail;

    private Product product;

    private EditProductVM editProductVM;
    private ViewHandler viewHandler;

    public void init(EditProductVM editProductVM, ViewHandler viewHandler, Product product) {
        this.editProductVM = editProductVM;
        this.viewHandler = viewHandler;
        this.product = product;

        name.textProperty().bindBidirectional(editProductVM.nameProperty());
        price.textProperty().bindBidirectional(editProductVM.priceProperty());
        detail.textProperty().bindBidirectional(editProductVM.detailProperty());

        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        detail.setText(product.getDetail());
    }


    public void onEditButton(ActionEvent actionEvent) throws RemoteException {
        String result = editProductVM.editProduct(product.getId());

        if("OK".equals(result)){
            editProductVM.clearFields();
            //open OverviewController window
            JOptionPane.showMessageDialog(null, "Edit successfully",null, JOptionPane.INFORMATION_MESSAGE);

        }else{
            JOptionPane.showMessageDialog(null, result,"Edit failed", JOptionPane.ERROR_MESSAGE);
        }
    }












}

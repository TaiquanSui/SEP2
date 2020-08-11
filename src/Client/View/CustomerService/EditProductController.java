package Client.View.CustomerService;

import Client.View.ViewHandler;
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

    public void init(EditProductVM editProductVM, ViewHandler viewHandler) {
        this.editProductVM = editProductVM;
        this.viewHandler = viewHandler;

        name.textProperty().bindBidirectional(editProductVM.nameProperty());
        price.textProperty().bindBidirectional(editProductVM.priceProperty());
        detail.textProperty().bindBidirectional(editProductVM.descriptionProperty());

        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        detail.setText(product.getDescription());
    }


    public void setValue(Product product){
        this.product = product;
        editProductVM.setValue(product);
    }


    public void onEditButton(ActionEvent actionEvent) throws RemoteException {
        String result = editProductVM.editProduct(product.getId());

        if("OK".equals(result)){
            editProductVM.clearFields();
            //open OverviewController window
            viewHandler.closeEditProductView();
            JOptionPane.showMessageDialog(null, "Edit successfully",null, JOptionPane.INFORMATION_MESSAGE);

        }else{
            JOptionPane.showMessageDialog(null, result,"Edit failed", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void onCancelButton(ActionEvent actionEvent) throws RemoteException {
        viewHandler.closeEditProductView();
    }



}

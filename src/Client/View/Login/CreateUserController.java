package Client.View.Login;

import Client.View.ViewHandler;
import Client.ViewModel.Login.CreateUserVM;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javax.swing.*;

public class CreateUserController {
    // setting up all the connections to various GUI elements: Text fields and labels.
    // These are automatically set by the JavaFX framework.
    // Notice the small icon to the left, indicating IntelliJ knows about the connection.
    // If the Icon is not there, something is wrong
    @FXML
    private TextField passwordAgainTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private RadioButton customer;
    @FXML
    private RadioButton administrator;



    private CreateUserVM createUserVM;
    private ViewHandler viewHandler;

    public void init(CreateUserVM createUserVM, ViewHandler viewHandler) {
        this.createUserVM = createUserVM;
        this.viewHandler = viewHandler;

        customer.setSelected(true);

        // setting up bidirectional bindings, so data can flow automatically between controller and view model
        idTextField.textProperty().bindBidirectional(createUserVM.IdProperty());
        passwordTextField.textProperty().bindBidirectional(createUserVM.passwordProperty());
        passwordAgainTextField.textProperty().bindBidirectional(createUserVM.passwordAgainProperty());
        nameTextField.textProperty().bindBidirectional(createUserVM.nameProperty());
        customer.selectedProperty().bindBidirectional(createUserVM.isCustomerProperty());
        administrator.selectedProperty().bindBidirectional(createUserVM.isAdministratorProperty());
    }

    // method called, when the content of the result label changes. This is changed from the View Model, based on the
    // result of the Model.
    // If all is okay, then I clear the fields, and open the login view, assuming the user has been created.
    private void onCreateUser(Observable observable, String old, String newVal) {
        if("OK".equals(newVal)) {
            createUserVM.clearFields();
            viewHandler.openLoginView();
        }
    }

    // method called, when the create user button is pressed.
    // make the View Model handle the request
    public void onCreateUserButton(ActionEvent actionEvent) {
        String result = createUserVM.attemptCreateUser();

        if("OK".equals(result)){
            createUserVM.clearFields();
            viewHandler.openLoginView();
        }else {
            JOptionPane.showMessageDialog(null, result,"User create failed", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void onCancelButton(ActionEvent actionEvent) {
        createUserVM.clearFields();
        viewHandler.openLoginView();
    }
}

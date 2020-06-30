package Client.View.Login;

import Client.View.ViewHandler;
import Client.ViewModel.Login.CreateUserVM;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;

public class CreateUserController {
    // setting up all the connections to various GUI elements: Text fields and labels.
    // These are automatically set by the JavaFX framework.
    // Notice the small icon to the left, indicating IntelliJ knows about the connection.
    // If the Icon is not there, something is wrong
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField passwordAgainTextField;
    @FXML
    private RadioButton isUser;
    @FXML
    private RadioButton isAdministrator;


    private CreateUserVM createUserVM;
    private ViewHandler viewHandler;

    public void init(CreateUserVM createUserVM, ViewHandler viewHandler) {
        this.createUserVM = createUserVM;
        this.viewHandler = viewHandler;

        isUser.setSelected(true);

        // setting up bidirectional bindings, so data can flow automatically between controller and view model
        emailTextField.textProperty().bindBidirectional(createUserVM.emailProperty());
        passwordTextField.textProperty().bindBidirectional(createUserVM.passwordProperty());
        passwordAgainTextField.textProperty().bindBidirectional(createUserVM.passwordAgainProperty());
        isUser.selectedProperty().bindBidirectional(createUserVM.isCustomerProperty());
        isAdministrator.selectedProperty().bindBidirectional(createUserVM.isAdministratorProperty());
    }


    // method called, when the create user button is pressed.
    // make the View Model handle the request
    public void onCreateUserButton(ActionEvent actionEvent) throws RemoteException {
        String result = createUserVM.attemptCreateUser();

        if("OK".equals(result)){
            createUserVM.clearFields();
            viewHandler.openLoginView();
            JOptionPane.showMessageDialog(null, result,"Account Successfully created", JOptionPane.INFORMATION_MESSAGE);

        }else {
            JOptionPane.showMessageDialog(null, result,"User create failed", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void onCancelButton(ActionEvent actionEvent) {
        createUserVM.clearFields();
        viewHandler.openLoginView();
    }
}

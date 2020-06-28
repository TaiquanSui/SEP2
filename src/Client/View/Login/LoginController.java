package Client.View.Login;

import Client.View.ViewHandler;
import Client.ViewModel.Login.LoginVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;

public class LoginController {
    @FXML
    private TextField IdTextField;
    @FXML
    private TextField passwordTextField;

    private LoginVM loginViewModel;
    private ViewHandler viewHandler;

    public void init(LoginVM lvm, ViewHandler viewHandlerLogin) {
        this.loginViewModel = lvm;
        this.viewHandler = viewHandlerLogin;

        IdTextField.textProperty().bindBidirectional(lvm.userNameProperty());
        passwordTextField.textProperty().bindBidirectional(lvm.passwordProperty());
    }


    // called when Login Button is pressed
    public void onLoginButton(ActionEvent actionEvent) throws RemoteException {
        String result = loginViewModel.validateLogin();

        if("OK".equals(result)){
            loginViewModel.clearFields();
            loginViewModel.registerClient();
            //open OverviewController window

        }else {
            // Login failed
            JOptionPane.showMessageDialog(null, result,"Login failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Called when Exit Button is pressed. Just terminating the application
    public void onExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    // Opening Create New User view.
    public void onCreateUserButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
        viewHandler.openCreateUserView();
    }

    // Opening Change Password view.
    public void onChangePassword(ActionEvent actionEvent) {
        loginViewModel.clearFields();
        viewHandler.openChangePasswordView();
    }
}

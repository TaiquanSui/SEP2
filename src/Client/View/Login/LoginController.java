package Client.View.Login;

import Client.View.ViewHandler;
import Client.ViewModel.Login.LoginVM;
import Shared.Model.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;

public class LoginController {

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;

    private LoginVM loginViewModel;
    private ViewHandler viewHandler;

    public void init(LoginVM lvm, ViewHandler viewHandler) {
        this.loginViewModel = lvm;
        this.viewHandler = viewHandler;

        emailTextField.textProperty().bindBidirectional(lvm.userNameProperty());
        passwordTextField.textProperty().bindBidirectional(lvm.passwordProperty());
    }


    // called when Login Button is pressed
    public void onLoginButton(ActionEvent actionEvent) throws RemoteException {
        String result = loginViewModel.validateLogin();

        if(UserType.Customer.toString().equals(result)){
            loginViewModel.clearFields();
            //open OverviewController window
            viewHandler.openOverview();

        }else if(UserType.Administrator.toString().equals(result)){
            loginViewModel.clearFields();
            //open OverviewController window
            viewHandler.openAdminOverview();

        }else{
            // Login failed
            JOptionPane.showMessageDialog(null, result,"Login failed", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Opening Create New User view.
    public void onCreateUserButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
        viewHandler.openCreateUserView();
    }

    // Opening Change Password view.
    public void onChangePasswordButton(ActionEvent actionEvent) {
        loginViewModel.clearFields();
        viewHandler.openChangePasswordView();
    }

    // Called when Exit Button is pressed. Just terminating the application
    public void onExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }
}

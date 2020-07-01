package Client.View.Login;

import Client.View.ViewHandler;
import Client.ViewModel.Login.LoginVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;

public class LoginController {
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private RadioButton UserRadioButton;
    @FXML
    private RadioButton AdministratorRadioButton;


    private LoginVM loginViewModel;
    private ViewHandler viewHandler;

    public void init(LoginVM lvm, ViewHandler viewHandlerLogin) {
        this.loginViewModel = lvm;
        this.viewHandler = viewHandlerLogin;

        UserRadioButton.setSelected(true);

        emailTextField.textProperty().bindBidirectional(lvm.userNameProperty());
        passwordTextField.textProperty().bindBidirectional(lvm.passwordProperty());
        UserRadioButton.selectedProperty().bindBidirectional(lvm.isUserProperty());
        AdministratorRadioButton.selectedProperty().bindBidirectional(lvm.isAdministratorProperty());
    }


    // called when Login Button is pressed
    public void onLoginButton(ActionEvent actionEvent) throws RemoteException {
        String result = loginViewModel.validateLogin();

        if("OK".equals(result)){
            loginViewModel.clearFields();
            //open OverviewController window
            JOptionPane.showMessageDialog(null, "Login successfully",null, JOptionPane.INFORMATION_MESSAGE);
            viewHandler.openSearchProductView();

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

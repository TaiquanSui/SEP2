package Client.View.Login;

import Client.View.ViewHandler;
import Client.ViewModel.Login.ChangePasswordVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;

public class ChangePasswordController {

    // GUI Objects set by JavaFX. The variable names case-match the names from the fx:id in the fxml file
    // In intellij, just to the left, you should see small icons indicating that intellij knows about the
    // connection to the fxml file
    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField oldPasswordTextField;
    @FXML
    public PasswordField newPasswordTextField;
    @FXML
    public PasswordField newPasswordAgainTextField;

    private ChangePasswordVM changePasswordVM;
    private ViewHandler viewHandler;

    // method called to setup everything, from the ViewHandler
    public void init(ChangePasswordVM cpvm, ViewHandler vh) {
        changePasswordVM = cpvm;
        viewHandler = vh;

        // making the bindings, so data can flow to the View Model, and back again, automatically.
        emailTextField.textProperty().bindBidirectional(changePasswordVM.emailProperty());
        oldPasswordTextField.textProperty().bindBidirectional(changePasswordVM.oldPasswordProperty());
        newPasswordTextField.textProperty().bindBidirectional(changePasswordVM.newPasswordProperty());
        newPasswordAgainTextField.textProperty().bindBidirectional(changePasswordVM.newPasswordAgainProperty());
    }


    // method called when the Update Password button is pressed
    public void onUpdatePasswordButton(ActionEvent actionEvent) throws RemoteException {
        String result = changePasswordVM.updatePassword();

        if("OK".equals(result)){
            changePasswordVM.clearFields();
            viewHandler.openLoginView();
            JOptionPane.showMessageDialog(null, "You have successfully changed your password","Attention", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, result,"Password change failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method called when the Cancel button is presseds
    public void onCancelButton(ActionEvent actionEvent) {
        changePasswordVM.clearFields();
        viewHandler.openLoginView();
    }

}

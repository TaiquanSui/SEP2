package Client.ViewModel.Login;

import Client.Model.Login.ILoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class ChangePasswordVM {

    // View model class for the change password view


    private final ILoginModel loginModel; // reference to the model interface. I don't know about the actual implementation

    // String properties to hold the data shown/created in the view
    private StringProperty email = new SimpleStringProperty();
    private StringProperty oldPassword = new SimpleStringProperty();
    private StringProperty newPassword = new SimpleStringProperty();
    private StringProperty newPasswordAgain = new SimpleStringProperty();


    // constructor
    public ChangePasswordVM(ILoginModel model) {
        loginModel = model;
    }


    // method called by the controller, when the user requests to update the password.
    // relevant information is retrieved from the properties, and forwarded to the model
    public String updatePassword() throws RemoteException {
        String result = loginModel.changePassword(email.getValue(), oldPassword.getValue(), newPassword.get(), newPasswordAgain.get());

        return result;
    }

    // method to clear the data in the properties
    public void clearFields() {
        email.setValue("");
        oldPassword.setValue("");
        newPassword.setValue("");
        newPasswordAgain.setValue("");
    }

    // methods to get the properties, so the controller can bind to them
    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty oldPasswordProperty() {
        return oldPassword;
    }

    public StringProperty newPasswordProperty() {
        return newPassword;
    }

    public StringProperty newPasswordAgainProperty() { return newPasswordAgain; }

}

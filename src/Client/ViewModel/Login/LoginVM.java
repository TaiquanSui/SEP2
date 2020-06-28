package Client.ViewModel.Login;

import Client.Model.Login.ILoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginVM {
    private final ILoginModel loginModel; // reference to the Model interface
    private StringProperty ID = new SimpleStringProperty(); // holds the username from the GUI textfield
    private StringProperty password = new SimpleStringProperty(); // holds the password from the GUI textfield

    public LoginVM(ILoginModel lm) {
        this.loginModel = lm;
    }

    public String validateLogin() {
        // I'm calling modelimpls void method here. I could have just returned the result instead.
        // But I'm trying to simulate the setup, if we had to call modelimpls server to validate the login credentials.
        // In that case, it would probably be better to add modelimpls listener to the model, to make it asynchronously.
        String result = loginModel.validateLogin(ID.getValue(), password.getValue());

        return result;
    }

    public void registerClient(){
        loginModel.registerClient(ID.getValue());
    }

    // method to clear the data in the properties
    public void clearFields() {
        ID.setValue("");
        password.setValue("");
    }

    // just get methods for all my properties
    public StringProperty userNameProperty() {
        return ID;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}

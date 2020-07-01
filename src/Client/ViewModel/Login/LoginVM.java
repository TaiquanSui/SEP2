package Client.ViewModel.Login;

import Client.Model.Login.ILoginModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class LoginVM {
    private final ILoginModel loginModel; // reference to the Model interface
    private StringProperty email = new SimpleStringProperty(); // holds the username from the GUI textfield
    private StringProperty password = new SimpleStringProperty(); // holds the password from the GUI textfield
    private BooleanProperty isUser = new SimpleBooleanProperty(); // holds the username from the GUI textfield
    private BooleanProperty isAdministrator = new SimpleBooleanProperty(); // holds the password from the GUI textfield

    public LoginVM(ILoginModel lm) {
        this.loginModel = lm;
    }

    public String validateLogin() throws RemoteException {
        // I'm calling modelimpls void method here. I could have just returned the result instead.
        // But I'm trying to simulate the setup, if we had to call modelimpls server to validate the login credentials.
        // In that case, it would probably be better to add modelimpls listener to the model, to make it asynchronously.

        if(email.getValue()==null || email.getValue().length()==0){
            String result = "Please enter the email";
            return result;
        }else if(password.getValue()==null || password.getValue().length()==0){
            String result = "Please enter the password";
            return result;
        }else {
            String result = loginModel.validateLogin(email.getValue(), password.getValue());
            return result;
        }
    }


    // method to clear the data in the properties
    public void clearFields() {
        email.setValue("");
        password.setValue("");
    }

    // just get methods for all my properties
    public StringProperty userNameProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public BooleanProperty isUserProperty() {
        return isUser;
    }

    public BooleanProperty isAdministratorProperty() {
        return isAdministrator;
    }
}

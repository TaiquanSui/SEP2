package Client.ViewModel.Login;

import Client.Model.Login.ILoginModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class CreateUserVM {
    // properties to hold the data shown/created in the controller
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty passwordAgain = new SimpleStringProperty();
    private BooleanProperty isCustomer = new SimpleBooleanProperty();
    private BooleanProperty isAdministrator = new SimpleBooleanProperty();

    private ILoginModel model;

    // constructor
    public CreateUserVM(ILoginModel loginModel) {
        model = loginModel;
    }

    // method to clear the data from the properties
    public void clearFields() {
        email.setValue("");
        password.setValue("");
        passwordAgain.setValue("");
    }

    // method called by the controller, when the user wants to create a new user
    // relevant data is retrieved from the properties, and forwarded to the model
    public String attemptCreateUser() throws RemoteException {
        if(email.getValue()==null || email.getValue().length()==0){
            String result = "Please enter the email";
            return result;
        }else if(password.getValue()==null || password.getValue().length()==0){
            String result = "Please enter the password";
            return result;
        }else if(passwordAgain.getValue()==null || passwordAgain.getValue().length()==0){
            String result = "Please enter the confirm password";
            return result;
        }else {
            String result = model.createUser(email.getValue(), password.getValue(), passwordAgain.getValue());
            return result;
        }
    }

    // methods to get access to the properties, so the controller can bind to them
    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty passwordAgainProperty() {
        return passwordAgain;
    }

    public BooleanProperty isCustomerProperty() {
        return isCustomer;
    }

    public BooleanProperty isAdministratorProperty(){ return isAdministrator;}
}

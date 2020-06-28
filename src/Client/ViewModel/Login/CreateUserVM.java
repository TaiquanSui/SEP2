package Client.ViewModel.Login;

import Client.Model.Login.ILoginModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateUserVM {
    // properties to hold the data shown/created in the controller
    private StringProperty Id = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
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
        Id.setValue("");
        name.setValue("");
        password.setValue("");
        passwordAgain.setValue("");
    }

    // method called by the controller, when the user wants to create a new user
    // relevant data is retrieved from the properties, and forwarded to the model
    public String attemptCreateUser() {
        String result = model.createUser(Id.getValue(), name.getValue(), password.getValue(), passwordAgain.getValue());

        return result;
    }

    // methods to get access to the properties, so the controller can bind to them
    public StringProperty IdProperty() {
        return Id;
    }

    public StringProperty nameProperty() {
        return name;
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

    public BooleanProperty isAdministratorProperty(){ return isAdministratorProperty();}
}

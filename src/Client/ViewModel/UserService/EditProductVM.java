package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Shared.Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class EditProductVM {

    private final IUserServiceModel userServiceModel;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty price = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public EditProductVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }


    public String editProduct(int id) throws RemoteException {
        // I'm calling modelimpls void method here. I could have just returned the result instead.
        // But I'm trying to simulate the setup, if we had to call modelimpls server to validate the login credentials.
        // In that case, it would probably be better to add modelimpls listener to the model, to make it asynchronously.

        if(name.getValue()==null){
            String result = "Please enter the name";
            return result;
        }else if(price.getValue()==null){
            String result = "Please enter the price";
            return result;
        }else if(description.getValue()==null) {
            String result = "Please enter the detail";
            return result;
        }

        try{
            double priceInDouble = Double.parseDouble(price.getValue());
            String result = userServiceModel.editProduct(id, name.getValue(), priceInDouble, description.getValue());
            return result;

        }catch(Exception e){
            String result = "Please enter the price correctly";
            return result;
        }

    }

    public void setValue(Product product){
        name.setValue(product.getName());
        price.setValue(String.valueOf(product.getPrice()));
        description.setValue(product.getDescription());
    }



    public void clearFields() {
        name.setValue("");
        price.setValue("");
        description.setValue("");
    }


    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty priceProperty() {
        return price;
    }
    public StringProperty descriptionProperty() {
        return description;
    }

}

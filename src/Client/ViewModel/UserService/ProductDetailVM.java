package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Shared.Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class ProductDetailVM {
    private final IUserServiceModel userServiceModel;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty price = new SimpleStringProperty();
    private StringProperty seller = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();


    public ProductDetailVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public void clearFields(){
        name.setValue("");
        price.setValue("");
        seller.setValue("");
        description.setValue("");
    }

    public void setValues(Product product){
        name.setValue(product.getName());
        price.setValue(String.valueOf(product.getPrice()));
        seller.setValue(product.getSeller());
        description.setValue(product.getDescription());
    }


    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty priceProperty() {
        return price;
    }
    public StringProperty sellerProperty() {
        return seller;
    }
    public StringProperty descriptionProperty() {
        return description;
    }

}

package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChatVM {

    private final IUserServiceModel iUserServiceModel; // reference to the Model interface


    public ChatVM(IUserServiceModel iUserServiceModel) {
        this.iUserServiceModel = iUserServiceModel;
    }
}

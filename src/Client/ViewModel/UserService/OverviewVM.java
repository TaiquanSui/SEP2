package Client.ViewModel.UserService;

import Client.Model.Login.ILoginModel;
import Client.Model.UserService.IUserServiceModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class OverviewVM {

    private StringProperty numOfMessages = new SimpleStringProperty();
    private final IUserServiceModel userServiceModel;

    public OverviewVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public void getNumOfMessages() throws RemoteException {
        String numOfMessagesToString = String.valueOf(userServiceModel.getNumOfMessages());
        Platform.runLater(() -> {
            numOfMessages.setValue(numOfMessagesToString);
        });
    }


    public StringProperty numOfMessageProperty(){
     return numOfMessages;
    }


}

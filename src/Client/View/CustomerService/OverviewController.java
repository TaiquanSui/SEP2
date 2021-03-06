package Client.View.CustomerService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.OverviewVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.rmi.RemoteException;


public class OverviewController {

    @FXML
    private Label numOfMessage;

    private OverviewVM overviewVM;
    private ViewHandler viewHandler;



    public void init(OverviewVM overviewVM, ViewHandler viewHandler) throws RemoteException {
        this.overviewVM = overviewVM;
        this.viewHandler = viewHandler;

        numOfMessage.textProperty().bindBidirectional(overviewVM.numOfMessageProperty());
        overviewVM.getNumOfMessages();
    }

    public void openSearchProductView(MouseEvent mouseEvent) {
        viewHandler.openSearchProductView();
    }

    public void openSellerOverview(MouseEvent mouseEvent){
        viewHandler.openSellerOverviewView();
    }

    public void openChatViewForOfflineMessages(MouseEvent mouseEvent) throws RemoteException{
        if(Integer.parseInt(overviewVM.numOfMessageProperty().getValue()) == 0){
            if (!viewHandler.openChatViewIfNoOfflineMessage()){
                JOptionPane.showMessageDialog(null, "You haven't create any session" ,"Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            viewHandler.openChatViewForOfflineMessages();
            overviewVM.getNumOfMessages();
        }
    }

    public void logout(ActionEvent actionEvent) throws RemoteException {
        overviewVM.logout();
        viewHandler.openLoginView();
    }


}

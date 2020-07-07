package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.EditProductVM;
import Client.ViewModel.UserService.OverviewVM;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.rmi.RemoteException;

public class OverviewController {

    private OverviewVM overviewVM;
    private ViewHandler viewHandler;



    public void init(OverviewVM overviewVM, ViewHandler viewHandler) {
        this.overviewVM = overviewVM;
        this.viewHandler = viewHandler;


    }



    public void openSearchProductView(ActionEvent actionEvent) throws RemoteException {
        viewHandler.openSearchProductView();
    }

    public void openSellerOverview(ActionEvent actionEvent) throws RemoteException {
        viewHandler.openSellerOverviewView();
    }






}

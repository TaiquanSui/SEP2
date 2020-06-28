package Client.Networking;

import Client.ShoppingApplication;
import javafx.application.Application;

import java.io.IOException;
import java.rmi.NotBoundException;

public class ClientMain {
    public static void main(String[] args) {

        try {
            ClientImpl cci = new ClientImpl();
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }

        Application.launch(ShoppingApplication.class);
    }
}

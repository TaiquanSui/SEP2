package Client;

import Client.Model.ModelFactory;
import Client.Networking.ClientImpl;
import Client.Networking.IClient;
import Client.View.ViewHandler;
import Client.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;

public class ShoppingApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // creating the core classes, that are needed.
        // If I had a client, I would create it here too, and hand over to the ModelFactory

        try {
            IClient client = new ClientImpl();
            ModelFactory mf = new ModelFactory(client);
            ViewModelFactory vmf = new ViewModelFactory(mf);
            ViewHandler vh = new ViewHandler(vmf);
            vh.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void stop() throws Exception {
        // This method is called, when the application is shut down
        System.out.println("Shutting down.");
    }
}

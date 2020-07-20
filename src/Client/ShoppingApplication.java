package Client;

import Client.Model.ModelFactory;
import Client.Networking.ClientImpl;
import Client.Networking.IClient;
import Client.View.ViewHandler;
import Client.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;


public class ShoppingApplication extends Application {

    private IClient client;

    @Override
    public void start(Stage stage) throws Exception {
        // creating the core classes, that are needed.
        // If I had a client, I would create it here too, and hand over to the ModelFactory

        try {
            client = new ClientImpl();
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
        client.logout();
        System.exit(0);
        System.out.println("Shutting down.");
    }


}

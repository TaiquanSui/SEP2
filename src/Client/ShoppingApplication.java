package Client;

import Client.Model.ModelFactory;
import Client.View.ViewHandler;
import Client.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ShoppingApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // creating the core classes, that are needed.
        // If I had a client, I would create it here too, and hand over to the ModelFactory

        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);
        vh.start();
    }

    @Override
    public void stop() throws Exception {
        // This method is called, when the application is shut down
        System.out.println("Shutting down.");
    }
}

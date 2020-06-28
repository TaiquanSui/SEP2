package Client.Model;

import Client.Model.Login.ILoginModel;
import Client.Model.Login.LoginModelImpl;
import Client.Networking.ClientImpl;
import Client.Networking.IClient;

public class ModelFactory {
    // class for creating and providing Model implementations. I just have one model in this case

    private LoginModelImpl loginModel;
    private IClient client;

    public ModelFactory(IClient client){
        this.client = client;
    }

    public ILoginModel getLoginModel() {
        // using lazy instantiation, meaning I only create the LoginModel, when it is asked for.
        // it is stored in a field variable, so the same instance can be reused again, and by multiple view models
        // This ensure all view models use the same instance of the model
        if(loginModel == null) {
            loginModel = new LoginModelImpl(client);
        }
        return loginModel;
    }
}

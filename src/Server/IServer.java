package Server;

import Client.Networking.IClient;
import Shared.Model.Message;
import Shared.Model.User;

import java.rmi.Remote;
import java.util.ArrayList;

public interface IServer extends Remote {
    boolean registerClient(IClient client, String name);

    void createNewUser(User user);
    void changePassword(String Id, String newPw);
    User getUser(String CustomerId);
    void logout(String name);

    void sendMessage(String msg, String name);
    ArrayList<Message> getMessages(String clientID);


}

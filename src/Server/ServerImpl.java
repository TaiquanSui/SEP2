package Server;

import Client.Networking.IClient;
import Shared.Model.Message;
import Shared.Model.User;

import java.util.ArrayList;

public class ServerImpl implements IServer{

    @Override
    public boolean registerClient(IClient client, String name) {
        return false;
    }

    @Override
    public void createNewUser(User user) {

    }

    @Override
    public void changePassword(String Id, String newPw) {

    }

    @Override
    public User getUser(String CustomerId) {
        return null;
    }

    @Override
    public void logout(String name) {

    }

    @Override
    public void sendMessage(String msg, String name) {

    }

    @Override
    public ArrayList<Message> getMessages(String clientID) {
        return null;
    }
}

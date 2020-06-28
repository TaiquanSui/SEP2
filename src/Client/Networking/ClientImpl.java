package Client.Networking;

import Shared.Model.User;

import java.rmi.RemoteException;

public class ClientImpl implements IClient{
    @Override
    public void registerClient(String id) throws RemoteException {

    }

    @Override
    public void sendMessage(String msg, String email) throws RemoteException {

    }

    @Override
    public void receiveMessage(String msg, String email) throws RemoteException {

    }

    @Override
    public void createNewUser(User user) throws RemoteException {

    }

    @Override
    public User getUser(String CustomerId) throws RemoteException {
        return null;
    }

    @Override
    public void changePassword(String Id, String newPw) throws RemoteException {

    }
}

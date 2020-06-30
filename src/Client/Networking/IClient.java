package Client.Networking;

import Shared.Model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void registerClient(String id) throws RemoteException;

    void sendMessage(String msg, String email) throws RemoteException;
    void receiveMessage(String msg, String email) throws RemoteException;

    boolean createNewUser(User user) throws RemoteException;
    User getUser(String email) throws RemoteException;
    void changePassword(String Id, String newPw) throws RemoteException;

}

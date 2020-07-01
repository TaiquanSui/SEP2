package Client.Networking;

import Server.IServer;
import Shared.Model.Product;
import Shared.Model.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ClientImpl implements IClient{

    private IServer server;


    public ClientImpl() throws RemoteException, NotBoundException
    {
        UnicastRemoteObject.exportObject(this, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 8080);
        server = (IServer)registry.lookup("Server");
    }

    @Override
    public void registerClient(String id) throws RemoteException {
        server.registerClient(this,id);
    }

    @Override
    public void sendMessage(String msg, String email) throws RemoteException {

    }

    @Override
    public void receiveMessage(String msg, String email) throws RemoteException {

    }

    @Override
    public boolean createNewUser(User user) throws RemoteException {
        return server.createNewUser(user);
    }

    @Override
    public User getUser(String email) throws RemoteException {
        return server.getUser(email);
    }

    @Override
    public void changePassword(String Id, String newPw) throws RemoteException {

    }

    @Override
    public ArrayList<Product> getProductList(String searchText) throws RemoteException {
        return server.getProductList(searchText);
    }
}

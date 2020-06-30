package Server;

import Client.Networking.IClient;
import Shared.Model.Message;
import Shared.Model.User;
import Shared.util.DBProduct;
import Shared.util.DBUser;
import Shared.util.DBUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements IServer{

    private List<ClientContainer> clients;

    private DBUtil dbUtil;
    private DBUser dbUser;
    private DBProduct dbProduct;


    public ServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        clients = new ArrayList<ClientContainer>();

        dbUtil = new DBUtil();
        dbUser = new DBUser();
        dbProduct = new DBProduct();
    }


    @Override
    public boolean registerClient(IClient client, String name) throws RemoteException {
        return false;
    }

    @Override
    public boolean createNewUser(User user) throws RemoteException {
        Connection con =null;

        boolean createUser = false;

        try {
            con = dbUtil.getCon();

            int addUser = dbUser.add(con, user);
            if(addUser==1){
                createUser = true;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return createUser;
    }

    @Override
    public void changePassword(String Id, String newPw) throws RemoteException {

    }

    @Override
    public User getUser(String email) throws RemoteException {
        Connection con =null;

        try {
            con= dbUtil.getCon();
            User currentUser=dbUser.login(con, email);

            return currentUser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void logout(String name) throws RemoteException {

    }

    @Override
    public void sendMessage(String msg, String name) throws RemoteException {

    }

    @Override
    public ArrayList<Message> getMessages(String clientID) throws RemoteException {
        return null;
    }


















    private class ClientContainer {
        String name;
        IClient client;

        ClientContainer(String name, IClient client) {
            this.name = name;
            this.client = client;
        }
    }










}

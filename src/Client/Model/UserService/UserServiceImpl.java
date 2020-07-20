package Client.Model.UserService;

import Client.Networking.IClient;
import Client.View.UserService.ChatViewController;
import Shared.Model.Message;
import Shared.Model.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserServiceImpl implements IUserServiceModel {

    private IClient client;

    public UserServiceImpl(IClient client) {
        this.client = client;
    }

    @Override
    public String getEmail() throws RemoteException {
        return client.getEmailOfUserLogin();
    }

    @Override
    public ArrayList<Product> getSearchResult(String searchText) throws RemoteException {
        return client.getProductList(searchText);
    }

    @Override
    public String addProduct(String name, double price, String description) throws RemoteException {
        String seller = client.getEmailOfUserLogin();
        Product product = new Product(name,price,description,seller);

        boolean result = client.addProduct(product);

        if(result){
            return "OK";
        }else {
            return "Server failed";
        }
    }

    @Override
    public String editProduct(int id, String name, double price, String detail) throws RemoteException {
        String seller = client.getEmailOfUserLogin();
        Product product = new Product(id,name,price,detail,seller);

        boolean result = client.editProduct(product);

        if(result){
            return "OK";
        }else {
            return "Server failed";
        }
    }

    @Override
    public String deleteProduct(String id) throws RemoteException {

        boolean result = client.deleteProduct(id);

        if(result){
            return "OK";
        }else {
            return "Server failed";
        }
    }

    @Override
    public ArrayList<Product> getSearchResultOfSeller(String searchText) throws RemoteException {
        return client.getSearchResultOfSeller(searchText);
    }

    @Override
    public void addChatViewToClient(ChatViewController chatViewController) throws RemoteException {
        client.setChatView(chatViewController);
    }

    @Override
    public boolean sendMessageToOnlineUser(Message message) throws RemoteException {
        return client.sendMessageToOnlineUser(message);
    }

    @Override
    public boolean sendMessageToOfflineUser(Message message) throws RemoteException {
        return client.sendMessageToOfflineUser(message);
    }

    @Override
    public ArrayList<Product> getAllProductsOnSale() throws RemoteException {
        return client.getAllProductsOnSale();
    }

    @Override
    public int getNumOfMessages(String email) throws RemoteException {
        return 0;
    }









    private class State implements Runnable{

        private PropertyChangeSupport support = new PropertyChangeSupport(this);
        private String email;

        public State(String email){
            this.email = email;
        }

        public void addListener(String name, PropertyChangeListener listener){
            support.addPropertyChangeListener(name, listener);
        };

        @Override
        public void run() {
            while (true){
                try {
                    String previousState = "";
                    String state = client.getUserStatus(email);
                    Thread.sleep(1000);
                    support.firePropertyChange("state",previousState,state);
                } catch (RemoteException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

















}

package Client.Model.UserService;

import Client.Networking.IClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class Status implements Runnable {

    private IClient client;
    private String email;
    private boolean run;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Status(IClient client) {
        this.client = client;
        run = false;
    }

    public void addListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void startThread(){ run = true; }

    public void stopThread(){
        run = false;
    }


    @Override
    public void run() {
        while (run){
            try {
                String status = client.getUserStatus(email);
                support.firePropertyChange("status","",status);
                Thread.sleep(2000);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





}

package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;
import Client.Model.UserService.Status;
import Client.View.CustomerService.ChatViewController;
import Shared.Model.Message;
import Shared.Model.Session;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChatVM {

    private final IUserServiceModel userServiceModel; // reference to the Model interface
    private final Status status;
    private final Thread statusThread;

    private StringProperty messageToSend = new SimpleStringProperty();
    private StringProperty userChattingWith = new SimpleStringProperty();
    private StringProperty chatterStatus = new SimpleStringProperty();

    public ChatVM(IUserServiceModel userServiceModel, Status status) {
        this.userServiceModel = userServiceModel;
        this.status = status;

        status.addListener("status", evt -> getUserStatus(evt));
        statusThread = new Thread(status);
    }


    public void addChatViewToClient(ChatViewController chatViewController) throws RemoteException{
        userServiceModel.addChatViewToClient(chatViewController);
    }


    public String getEmail() throws RemoteException {
        return userServiceModel.getEmail();
    }

    public Message createNewMessage(String receiverEmail) throws RemoteException{
        String messageText = messageToSend.getValue();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(calendar.getTime());


        Message messageToSend = new Message(messageText,time,getEmail(),receiverEmail);

        return messageToSend;
    }


    public boolean sendMessage(Message message) throws RemoteException{
        return userServiceModel.sendMessage(message);
    }


    public void startStatusThread(String email){

        System.out.println(String.valueOf(statusThread.isAlive()));

        if(statusThread.isAlive()){
            status.setEmail(email);
        }else {
            status.setEmail(email);
            status.startThread();
            statusThread.start();
        }

    }

    public void endStatusThread(){
        status.stopThread();
    }

    public ArrayList<Session> getOfflineMessages() throws RemoteException{
        return userServiceModel.getOfflineMessages();
    }








    public StringProperty messageProperty() {
        return messageToSend;
    }
    public StringProperty userChattingWithProperty() {
        return userChattingWith;
    }
    public StringProperty chatterStatusProperty() {
        return chatterStatus;
    }








    private void getUserStatus(PropertyChangeEvent propertyChangeEvent) {
        String result = (String)propertyChangeEvent.getNewValue();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                chatterStatus.setValue(result);
            }
        });
    }





}

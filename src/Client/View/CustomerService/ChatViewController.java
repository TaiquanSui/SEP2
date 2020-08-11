package Client.View.CustomerService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.ChatVM;
import Shared.Model.Message;
import Shared.Model.Session;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatViewController {


    @FXML
    private ListView<String> userListView;
    @FXML
    private ListView<Message> chatListView;
    @FXML
    private Label userChattingWith;
    @FXML
    private Label status;
    @FXML
    private TextArea messageArea;

    private ArrayList<Session> sessions;
    private ObservableList<String> chatters;
    private ObservableList<Message> chatMessages;

    private ChatVM chatVM;
    private ViewHandler viewHandler;

    public void init(ChatVM cvm, ViewHandler viewHandler) throws RemoteException {
        this.chatVM = cvm;
        this.viewHandler = viewHandler;

        sessions = new ArrayList<Session>();

        chatters = FXCollections.observableArrayList();
        chatMessages = FXCollections.observableArrayList();

        userListView.setItems(chatters);
        chatListView.setItems(chatMessages);

        // Binding the label to the property which will be updated with the login result
        // Using bidirectional, so the textfield can be cleared from the VM
        messageArea.textProperty().bindBidirectional(chatVM.messageProperty());
        userChattingWith.textProperty().bindBidirectional(chatVM.userChattingWithProperty());
        status.textProperty().bindBidirectional(chatVM.chatterStatusProperty());

        initChatListView();

        chatVM.addChatViewToClient(this);


        userListView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                Platform.runLater(() -> {
                    if(newValue!=null) {
                        for (Session session : sessions) {
                            if (session.getSenderEmail().equals(newValue)) {
                                chatMessages.setAll(session.getMessages());
                            }
                        }
                        Platform.runLater(() -> {
                            chatVM.startStatusThread(newValue);
                        });
                        userChattingWith.textProperty().setValue(newValue);
                    }
                });
            }
        );
    }


    public boolean haveChatter(){
        boolean haveChatter = sessions.size() != 0;

        return haveChatter;
    }


    public boolean checkChatter(String emailOfChatter){
        for (int i = 0; i<chatters.size(); i++){
            if(sessions.get(i).getSenderEmail().equals(emailOfChatter)){
                return true;
            }
        }
        return false;
    }



    public void addChatter(String emailOfChatter) {
        Session session = new Session(emailOfChatter);
        sessions.add(session);
        chatters.add(emailOfChatter);

        userListView.getSelectionModel().select(emailOfChatter);
    }


    public void endStatusThread(){
        chatVM.endStatusThread();
    }



    public void receiveMessage(Message message){

        Platform.runLater(() -> {
            viewHandler.openChatView(message.getSenderEmail());

            if(checkChatter(message.getSenderEmail())){

                for (Session session : sessions) {
                    if (message.getSenderEmail().equals(session.getSenderEmail())) {
                        session.addMessage(message);
                    }
                }

                userListView.getSelectionModel().select(message.getSenderEmail());
            }else {
                addChatter(message.getSenderEmail());
                chatMessages.add(message);
            }

        });


    }



    public boolean getOfflineMessages() throws RemoteException{

        ArrayList<Session> offlineMessages = chatVM.getOfflineMessages();

        if(offlineMessages.size() == 0){
            return false;
        }else {
            sessions.addAll(offlineMessages);

            for(Session session : offlineMessages){
                if(!checkChatter(session.getSenderEmail())) {
                    chatters.add(session.getSenderEmail());
                }
            }

            userListView.getSelectionModel().select(0);
            return true;
        }
    }












    //Method use to handle button press that submits the 1st user's text to the listview.
    @FXML
    private void OnSendMessageButton(MouseEvent event) throws RemoteException {

        String emailOfChatter = userListView.getSelectionModel().selectedItemProperty().getValue();
        Message message = chatVM.createNewMessage(emailOfChatter);
        messageArea.setText("");  //clear 1st user's textfield

        boolean result = chatVM.sendMessage(message);

        if (result){
            chatMessages.add(message);  //get 1st user's text from his/her textfield and add message to observablelist
        }else {

        }
    }


























    private void initChatListView(){
        Platform.runLater(() -> {
            chatListView.setCellFactory(param -> {
                ListCell<Message> cell = new ListCell<Message>(){
                    Label lblUserLeft = new Label();
                    Label lblTextLeft = new Label();
                    HBox hBoxLeft = new HBox(lblUserLeft, lblTextLeft);

                    Label lblUserRight = new Label();
                    Label lblTextRight = new Label();
                    HBox hBoxRight = new HBox(lblTextRight, lblUserRight);

                    {
                        hBoxLeft.setAlignment(Pos.CENTER_LEFT);
                        hBoxLeft.setSpacing(5);
                        hBoxRight.setAlignment(Pos.CENTER_RIGHT);
                        hBoxRight.setSpacing(5);
                    }
                    @Override
                    protected void updateItem(Message item, boolean empty) {
                        super.updateItem(item, empty);

                        if(empty)
                        {
                            setText(null);
                            setGraphic(null);
                        }
                        else{
                            try {
                                if(item.getSenderEmail().equals(chatVM.getEmail()))
                                {
                                    lblUserRight.setText(":" + item.getSenderEmail());
                                    lblTextRight.setText(item.getText());
                                    setGraphic(hBoxRight);
                                }
                                else{
                                    lblUserLeft.setText(item.getSenderEmail() + ":");
                                    lblTextLeft.setText(item.getText());
                                    setGraphic(hBoxLeft);
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                };

                return cell;
            });
        });

    }













}

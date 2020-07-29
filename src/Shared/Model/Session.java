package Shared.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Session implements Serializable {

    private String senderEmail;
    private ArrayList<Message> messages;


    public Session(String senderEmail){
        this.senderEmail = senderEmail;
        messages = new ArrayList<Message>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
}

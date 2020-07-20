package Shared.Model;

import java.io.Serializable;

public class Message implements Serializable {

    private String text;
    private String date;
    private String senderEmail;
    private String receiverEmail;
    private boolean read;

    public Message(String text, String date, String senderEmail, String receiverEmail, boolean read)
    {
        this.text = text;
        this.date = date;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.read = read;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}

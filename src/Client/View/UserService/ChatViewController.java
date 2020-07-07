package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.Login.LoginVM;
import Client.ViewModel.UserService.ChatVM;
import Shared.Model.Product;

public class ChatViewController {

    private String emailOfChatter;

    private ChatVM chatVM;
    private ViewHandler viewHandler;

    public void init(ChatVM cvm, ViewHandler viewHandler, String emailOfChatter) {
        this.chatVM = cvm;
        this.viewHandler = viewHandler;
        this.emailOfChatter =emailOfChatter;
    }























}

package Client.ViewModel;

import Client.Model.ModelFactory;
import Client.ViewModel.Login.ChangePasswordVM;
import Client.ViewModel.Login.CreateUserVM;
import Client.ViewModel.Login.LoginVM;
import Client.ViewModel.UserService.*;

import java.rmi.RemoteException;

public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private LoginVM loginVM;
    private CreateUserVM createUserVM;
    private ChangePasswordVM changePasswordVM;

    private OverviewVM overviewVM;

    private SearchProductVM searchProductVM;

    private SellerOverviewVM sellerOverviewVM;
    private AddProductVM addProductVM;
    private EditProductVM editProductVM;
    private ChatVM chatVM;


    public ViewModelFactory(ModelFactory mf) {
        this.modelFactory = mf;
    }




    public LoginVM getLoginVM() {
        // using lazy instantiation, to ensure only one LoginVM is created, and it can subsequently be reused
        // I could also have instantiated them in modelimpls constructor
        if(loginVM == null) {
            loginVM = new LoginVM(modelFactory.getLoginModel());
        }
        return loginVM;
    }

    public CreateUserVM getCreateUserVM() {
        if(createUserVM == null) {
            createUserVM = new CreateUserVM(modelFactory.getLoginModel());
        }
        return createUserVM;
    }

    public ChangePasswordVM getChangePasswordVM() {
        if(changePasswordVM == null) {
            changePasswordVM = new ChangePasswordVM(modelFactory.getLoginModel());
        }
        return changePasswordVM;
    }








    public OverviewVM getOverviewVM() {
        if(overviewVM == null) {
            overviewVM = new OverviewVM(modelFactory.getUserServiceModel());
        }
        return overviewVM;
    }








    public SearchProductVM getSearchProductVM() {
        if(searchProductVM == null) {
            searchProductVM = new SearchProductVM(modelFactory.getUserServiceModel());
        }
        return searchProductVM;
    }






    public SellerOverviewVM getSellerOverviewVM() {
        if(sellerOverviewVM == null) {
            sellerOverviewVM = new SellerOverviewVM(modelFactory.getUserServiceModel());
        }
        return sellerOverviewVM;
    }

    public AddProductVM getAddProductVM() {
        if(addProductVM == null) {
            addProductVM = new AddProductVM(modelFactory.getUserServiceModel());
        }
        return addProductVM;
    }

    public EditProductVM getEditProductVM() {
        if(editProductVM == null) {
            editProductVM = new EditProductVM(modelFactory.getUserServiceModel());
        }
        return editProductVM;
    }





    public ChatVM getChatVM() {
        if(chatVM == null) {
            chatVM = new ChatVM(modelFactory.getUserServiceModel(),modelFactory.getStatus());
        }
        return chatVM;
    }







}

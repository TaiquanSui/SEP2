package Client.ViewModel;

import Client.Model.ModelFactory;
import Client.ViewModel.Login.ChangePasswordVM;
import Client.ViewModel.Login.CreateUserVM;
import Client.ViewModel.Login.LoginVM;

public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private LoginVM loginVM;
    private CreateUserVM createUserVM;
    private ChangePasswordVM changePasswordVM;

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
}

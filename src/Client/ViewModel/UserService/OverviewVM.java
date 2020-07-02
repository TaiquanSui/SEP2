package Client.ViewModel.UserService;

import Client.Model.Login.ILoginModel;
import Client.Model.UserService.IUserServiceModel;

public class OverviewVM {
    private final IUserServiceModel userServiceModel;

    public OverviewVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }
}

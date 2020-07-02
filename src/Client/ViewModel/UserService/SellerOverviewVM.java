package Client.ViewModel.UserService;

import Client.Model.UserService.IUserServiceModel;

public class SellerOverviewVM {
    private final IUserServiceModel userServiceModel;

    public SellerOverviewVM(IUserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }
}

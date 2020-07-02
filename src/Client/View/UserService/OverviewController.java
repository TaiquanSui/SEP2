package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.EditProductVM;
import Client.ViewModel.UserService.OverviewVM;

public class OverviewController {

    private OverviewVM overviewVM;
    private ViewHandler viewHandler;

    public void init(OverviewVM overviewVM, ViewHandler viewHandler) {
        this.overviewVM = overviewVM;
        this.viewHandler = viewHandler;

    }
}

package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.AddProductVM;
import Client.ViewModel.UserService.EditProductVM;

public class EditProductController {

    private EditProductVM editProductVM;
    private ViewHandler viewHandler;

    public void init(EditProductVM editProductVM, ViewHandler viewHandler) {
        this.editProductVM = editProductVM;
        this.viewHandler = viewHandler;

    }
}

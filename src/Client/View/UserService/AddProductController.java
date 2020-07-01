package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.AddProductVM;
import Client.ViewModel.UserService.SearchProductVM;
import Shared.Model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddProductController {

    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextArea detail;

    @FXML
    private Button add;
    @FXML
    private Button cancel;

    private AddProductVM addProductVM;
    private ViewHandler viewHandler;

    public void init(AddProductVM addProductVM, ViewHandler viewHandler) {
        this.addProductVM = addProductVM;
        this.viewHandler = viewHandler;

    }







}

package Client.View.CustomerService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.SearchProductVM;
import Shared.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SearchProductController {

    @FXML
    private Menu menu;
    @FXML
    private MenuItem logout;
    @FXML
    private TextField searchText;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private TableColumn<Product, String> sellerColumn;

    private SearchProductVM searchProductVM;
    private ViewHandler viewHandler;


    public void init(SearchProductVM searchProductVM, ViewHandler viewHandler) {
        this.searchProductVM = searchProductVM;
        this.viewHandler = viewHandler;

        searchText.textProperty().bindBidirectional(searchProductVM.searchProperty());

        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        sellerColumn.setCellValueFactory(new PropertyValueFactory("seller"));


        productTableView.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product rowData = row.getItem();
                    viewHandler.openProductDetailView(rowData);
                }
            });
            return row ;
        });

    }


    public void searchProduct(ActionEvent actionEvent) throws RemoteException{

        ArrayList<Product> productArrayList = searchProductVM.getSearchResult();

        if(productArrayList==null){
            JOptionPane.showMessageDialog(null, "No result","Fail", JOptionPane.ERROR_MESSAGE);
        }else {
            ObservableList<Product> productList = FXCollections.observableArrayList();
            productList.addAll(productArrayList);

            productTableView.setItems(productList);
        }
    }


    public void clickBack(MouseEvent mouseEvent) {
        viewHandler.openOverview();
    }


    public void logout(ActionEvent actionEvent) throws RemoteException {

        JOptionPane.showMessageDialog(null, "","User create failed", JOptionPane.ERROR_MESSAGE);

    }



}

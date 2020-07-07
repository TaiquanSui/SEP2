package Client.View.UserService;

import Client.View.ViewHandler;
import Client.ViewModel.UserService.OverviewVM;
import Client.ViewModel.UserService.SearchProductVM;
import Client.ViewModel.UserService.SellerOverviewVM;
import Shared.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SellerOverviewController {

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

    private SellerOverviewVM sellerOverviewVM;
    private ViewHandler viewHandler;



    public void init(SellerOverviewVM sellerOverviewVM, ViewHandler viewHandler) {
        this.sellerOverviewVM = sellerOverviewVM;
        this.viewHandler = viewHandler;

        searchText.textProperty().bindBidirectional(sellerOverviewVM.searchProperty());

        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));

        productTableView.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product rowData = row.getItem();

                    viewHandler.openEditProductView(rowData);
                }
            });
            return row ;
        });
    }



    public void getSellerProductList(ActionEvent actionEvent) throws RemoteException {
        ArrayList<Product> productArrayList = sellerOverviewVM.getSellerProductList();

        ObservableList<Product> productList = FXCollections.observableArrayList();
        productArrayList.addAll(productArrayList);

        productTableView.setItems(productList);

    }


    public void addProduct(ActionEvent actionEvent) throws RemoteException {
        viewHandler.openAddProductView();
    }


    public void editProduct(ActionEvent actionEvent) throws RemoteException {
        Product productSelected = productTableView.getSelectionModel().selectedItemProperty().getValue();
        viewHandler.openEditProductView(productSelected);
    }




    public void logout(ActionEvent actionEvent) throws RemoteException {

        JOptionPane.showMessageDialog(null, "","User create failed", JOptionPane.ERROR_MESSAGE);

    }



}

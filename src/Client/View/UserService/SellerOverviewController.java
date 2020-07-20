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
import javafx.scene.input.MouseEvent;

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
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;


    private SellerOverviewVM sellerOverviewVM;
    private ViewHandler viewHandler;



    public void init(SellerOverviewVM sellerOverviewVM, ViewHandler viewHandler) throws RemoteException {
        this.sellerOverviewVM = sellerOverviewVM;
        this.viewHandler = viewHandler;

        searchText.textProperty().bindBidirectional(sellerOverviewVM.searchProperty());

        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));

        getAllProductsOnSale();

        /**
         *   open edit product view when double click product row
         */
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



    public void getSearchResultOfSeller(ActionEvent actionEvent) throws RemoteException {
        ArrayList<Product> searchResult = sellerOverviewVM.getSearchResultOfSeller();

        ObservableList<Product> productList = FXCollections.observableArrayList();

        productList.addAll(searchResult);

        productTableView.setItems(productList);
    }


    public void addProduct(ActionEvent actionEvent) throws RemoteException {
        viewHandler.openAddProductView(this);
    }


    public void editProduct(ActionEvent actionEvent) throws RemoteException {
        Product productSelected = productTableView.getSelectionModel().selectedItemProperty().getValue();
        viewHandler.openEditProductView(productSelected);
    }

    public void deleteProduct(ActionEvent actionEvent) throws RemoteException {
        Product productSelected = productTableView.getSelectionModel().selectedItemProperty().getValue();

        String result = "";

        try{
            String id = String.valueOf(productSelected.getId());
            result = sellerOverviewVM.deleteProduct(id);
        }catch (Exception e){
            result = "client exception";
        }

        if("OK".equals(result)){
            sellerOverviewVM.clearFields();
            //open OverviewController window
            getAllProductsOnSale();
        }else{
            JOptionPane.showMessageDialog(null, result,"Fail", JOptionPane.ERROR_MESSAGE);
        }

    }


    public void clickBack(MouseEvent mouseEvent) {
        viewHandler.openOverview();
    }









    public void getAllProductsOnSale() throws RemoteException {
        /**
         *   get the list of products on sale
         */
        ArrayList<Product> productArrayList = sellerOverviewVM.getAllProductsOnSale();

        ObservableList<Product> productList = FXCollections.observableArrayList();

        productList.addAll(productArrayList);

        productTableView.setItems(productList);
    }


    public void logout(ActionEvent actionEvent) throws RemoteException {

        JOptionPane.showMessageDialog(null, "","User create failed", JOptionPane.ERROR_MESSAGE);

    }



}

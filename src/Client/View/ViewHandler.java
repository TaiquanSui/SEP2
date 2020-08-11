package Client.View;

import Client.View.AdminService.AdminOverviewController;
import Client.View.Login.ChangePasswordController;
import Client.View.Login.CreateUserController;
import Client.View.Login.LoginController;
import Client.View.CustomerService.*;
import Client.ViewModel.ViewModelFactory;
import Shared.Model.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;

public class ViewHandler {

    private final ViewModelFactory viewModelFactory;
    private Stage mainStage;


    public ViewHandler(ViewModelFactory lvm) {
        this.viewModelFactory = lvm;
        mainStage = new Stage();
    }


    public void start() {
        // opening first view
        openLoginView();
        initChatView();
        mainStage.show();
    }





    private Stage chatStage;
    private Scene chatScene;
    private ChatViewController chatViewController;


    public void initChatView(){
        try{
            if(chatStage == null) {
                chatStage = new Stage();
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomerService/ChatView.fxml"));
            Parent root = loader.load();
            chatViewController = loader.getController();
            chatViewController.init(viewModelFactory.getChatVM(), this);
            chatScene = new Scene(root);

            chatStage.setTitle("Chat");
            chatStage.setScene(chatScene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openChatView(String chatterEmail) {

        if(chatterEmail != null  && !chatViewController.checkChatter(chatterEmail)){
            chatViewController.addChatter(chatterEmail);
        }
        chatStage.setOnCloseRequest(evt -> chatViewController.endStatusThread());

        if(!chatStage.isShowing()) {
            chatStage.show();
        }

    }

    public boolean openChatViewIfNoOfflineMessage() {
        if(chatViewController.haveChatter()){
            chatStage.setOnCloseRequest(evt -> chatViewController.endStatusThread());

            if(!chatStage.isShowing()) {
                chatStage.show();
            }
            return true;
        }else {
            return false;
        }
    }

    public void openChatViewForOfflineMessages(){
        try {
            boolean hasOfflineMessage = chatViewController.getOfflineMessages();

            chatStage.setOnCloseRequest(evt -> chatViewController.endStatusThread());

            if(hasOfflineMessage && !chatStage.isShowing()) {
                chatStage.show();
            }else {
                JOptionPane.showMessageDialog(null, "no offline message","error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }











    private Scene loginScene;
    public void openLoginView() {
        try {
            // I check if the scene has already been created.
            // storing the scene for future use, so I do not have to load it and initialize the controller
            // multiple times.
            if(loginScene == null) {
                FXMLLoader loader = new FXMLLoader(); // creating fx loader, which can load fxml and create controller

                loader.setLocation(getClass().getResource("Login/Login.fxml"));

                // loading scene root. This contains every object in the scene
                // It's a tree structure similar to XML or HTML
                Parent root = loader.load();

                // getting the controller. JavaFX creates this and makes the connection between fxml file and controller
                LoginController view = loader.getController();

                // initializing my controller. I need this method, because we do not have access to the constructor
                view.init(viewModelFactory.getLoginVM(), this);

                // storing the created scene for future use
                loginScene = new Scene(root);
            }
            // setting title of the window
            mainStage.setTitle("Log in");

            // putting my scene into the window
            mainStage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Scene createUserScene;
    public void openCreateUserView() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(createUserScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("Login/CreateUser.fxml"));
                Parent root = loader.load();

                CreateUserController view = loader.getController();
                view.init(viewModelFactory.getCreateUserVM(), this);

                // storing scene in field variable for future use
                createUserScene = new Scene(root);
            }
            mainStage.setTitle("Create User");
            mainStage.setScene(createUserScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Scene changePWScene;
    public void openChangePasswordView() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(changePWScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("Login/ChangePassword.fxml"));
                Parent root = loader.load();

                ChangePasswordController view = loader.getController();
                view.init(viewModelFactory.getChangePasswordVM(), this);

                // storing scene in field variable for future use
                changePWScene = new Scene(root);
            }
            mainStage.setTitle("Change password");
            mainStage.setScene(changePWScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    private Scene adminScene;
    public void openAdminOverview() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(adminScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("AdminService/AdminOverview.fxml"));
                Parent root = loader.load();

                AdminOverviewController view = loader.getController();
                view.init(viewModelFactory.getAdminOverviewVM(), this);

                // storing scene in field variable for future use
                adminScene = new Scene(root);
            }
            mainStage.setTitle("Admin");
            mainStage.setScene(adminScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }















    private Scene overviewScene;
    public void openOverview() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(overviewScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("CustomerService/Overview.fxml"));
                Parent root = loader.load();

                OverviewController view = loader.getController();
                view.init(viewModelFactory.getOverviewVM(), this);

                // storing scene in field variable for future use
                overviewScene = new Scene(root);
            }
            mainStage.setTitle("Overview");
            mainStage.setScene(overviewScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private Scene searchProductScene;
    public void openSearchProductView() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(searchProductScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("CustomerService/SearchProduct.fxml"));
                Parent root = loader.load();

                SearchProductController view = loader.getController();
                view.init(viewModelFactory.getSearchProductVM(), this);

                // storing scene in field variable for future use
                searchProductScene = new Scene(root);
            }
            mainStage.setTitle("Search Product");
            mainStage.setScene(searchProductScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage productDetailStage;
    private Scene productDetailScene;
    private ProductDetailController productDetailController;
    public void openProductDetailView(Product product) {
        try {
            if(productDetailStage == null) {
                productDetailStage = new Stage();
            }

            // no need to load the same scene more than once. I can just reuse it
            if(productDetailScene == null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CustomerService/ProductDetail.fxml"));
                Parent root = loader.load();
                productDetailController = loader.getController();

                productDetailController.init(this,viewModelFactory.getProductDetailVM());
                // storing scene in field variable for future use
                productDetailScene = new Scene(root);

            }
            productDetailController.setValues(product);

            productDetailStage.setTitle("Product Detail");
            productDetailStage.setScene(productDetailScene);
            productDetailStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeProductDetailView(){
        productDetailStage.close();
    }





    private Scene sellerOverviewScene;
    public void openSellerOverviewView() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(sellerOverviewScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("CustomerService/SellerOverview.fxml"));
                Parent root = loader.load();

                SellerOverviewController view = loader.getController();
                view.init(viewModelFactory.getSellerOverviewVM(), this);

                // storing scene in field variable for future use
                sellerOverviewScene = new Scene(root);
            }
            mainStage.setTitle("Seller Overview");
            mainStage.setScene(sellerOverviewScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private Stage addProductStage;
    private Scene addProductScene;
    public void openAddProductView(SellerOverviewController sc) {
        try {
            if(addProductStage == null) {
                addProductStage = new Stage();
            }
            // no need to load the same scene more than once. I can just reuse it
            if(addProductScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("CustomerService/AddProduct.fxml"));
                Parent root = loader.load();

                AddProductController view = loader.getController();
                view.init(viewModelFactory.getAddProductVM(), sc, this);

                // storing scene in field variable for future use
                addProductScene = new Scene(root);

            }
            addProductStage.setTitle("Add Product");
            addProductStage.setScene(addProductScene);
            addProductStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAddProductView(){
        addProductStage.close();
    }






    private Stage editProductStage;
    private Scene editProductScene;
    private EditProductController editProductController;
    public void openEditProductView(Product product) {
        try {
            if(editProductStage == null) {
                editProductStage = new Stage();
            }

            // no need to load the same scene more than once. I can just reuse it
            if(editProductScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("CustomerService/EditProduct.fxml"));
                Parent root = loader.load();

                editProductController = loader.getController();
                editProductController.init(viewModelFactory.getEditProductVM(), this);

                // storing scene in field variable for future use
                editProductScene = new Scene(root);
            }
            
            editProductController.setValue(product);
            editProductStage.setTitle("Edit Product");
            editProductStage.setScene(editProductScene);
            editProductStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeEditProductView(){
        editProductStage.close();
    }




}

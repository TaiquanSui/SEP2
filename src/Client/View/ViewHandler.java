package Client.View;

import Client.View.Login.ChangePasswordController;
import Client.View.Login.CreateUserController;
import Client.View.Login.LoginController;
import Client.View.UserService.*;
import Client.ViewModel.ViewModelFactory;
import Shared.Model.Product;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class ViewHandler {

    private final ViewModelFactory viewModelFactory;
    private Stage mainStage;


    public ViewHandler(ViewModelFactory lvm) {
        this.viewModelFactory = lvm;
        mainStage = new Stage();
    }

    // I could do this in the constructor. It's just personal preference to make the constructor only create things
    // and not start all kinds of stuff.
    public void start() {
        // opening first view
        openLoginView();
        mainStage.show();
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


    private Scene overviewScene;
    public void openOverview() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(overviewScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("UserService/Overview.fxml"));
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

                loader.setLocation(getClass().getResource("UserService/SearchProduct.fxml"));
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


    private Scene sellerOverviewScene;
    public void openSellerOverviewView() {
        try {
            // no need to load the same scene more than once. I can just reuse it
            if(sellerOverviewScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("UserService/SellerOverview.fxml"));
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

                loader.setLocation(getClass().getResource("UserService/AddProduct.fxml"));
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
    public void openEditProductView(Product product) {
        try {
            if(editProductStage == null) {
                editProductStage = new Stage();
            }

            // no need to load the same scene more than once. I can just reuse it
            if(editProductScene == null) {
                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("UserService/EditProduct.fxml"));
                Parent root = loader.load();

                EditProductController view = loader.getController();
                view.init(viewModelFactory.getEditProductVM(), this, product);

                // storing scene in field variable for future use
                editProductScene = new Scene(root);
            }
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



    private Stage chatStage;
    private Scene chatScene;
    public void openChatView(String chatterEmail) {
        try {
            if(chatStage == null) {
                chatStage = new Stage();
            }

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("UserService/ChatView.fxml"));
            Parent root = loader.load();
            ChatViewController view = loader.getController();

            // no need to load the same scene more than once. I can just reuse it
            if(chatScene == null) {
                view.init(viewModelFactory.getChatVM(), this);
                view.addChatter(chatterEmail);
                chatStage.setOnCloseRequest(evt -> view.endStatusThread());

                // storing scene in field variable for future use
                chatScene = new Scene(root);
            }else {
                view.addChatter(chatterEmail);
                chatStage.setOnCloseRequest(evt -> view.endStatusThread());
            }

            chatStage.setTitle("Chat");
            chatStage.setScene(chatScene);

            if(!chatStage.isShowing()) {
                chatStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeChatView(){
        chatStage.close();
    }




}

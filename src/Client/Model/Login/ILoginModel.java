package Client.Model.Login;

import Shared.Model.UserType;

import java.rmi.RemoteException;

public interface ILoginModel {

    String validateLogin(String email, String password) throws RemoteException;

    String createUser(String email, String pw, String pwAgain, UserType userType) throws RemoteException;

    String changePassword(String email, String pw, String newPw, String newPwAgain) throws RemoteException;

}

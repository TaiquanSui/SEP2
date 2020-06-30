package Client.Model.Login;

import java.rmi.RemoteException;

public interface ILoginModel {

    void registerClient(String Id) throws RemoteException;

    String validateLogin(String email, String password) throws RemoteException;

    String createUser(String email, String pw, String pwAgain) throws RemoteException;

    String changePassword(String email, String pw, String newPw, String newPwAgain) throws RemoteException;

}

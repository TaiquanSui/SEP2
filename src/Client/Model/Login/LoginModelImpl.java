package Client.Model.Login;

import Client.Networking.IClient;
import Shared.Model.User;
import Shared.Model.UserType;

import java.rmi.RemoteException;

public class LoginModelImpl implements ILoginModel{

    private IClient client;

    public LoginModelImpl(IClient client) {
        this.client = client;
    }


    @Override
    public String validateLogin(String email, String password) throws RemoteException{
        String result = checkLoginForSignIn(email, password);

        return result;
    }

    @Override
    public String createUser(String email, String pw, String pwAgain, UserType userType) throws RemoteException {
        String result = attemptCreateUser(email, pw, pwAgain, userType);

        if("OK".equals(result)) {
            boolean createAccount = client.createNewUser(new User(email, pw, userType));
            if(!createAccount){
                result = "server failed";
            }
        }

        return result;
    }

    @Override
    public String changePassword(String email, String pw, String newPw, String newPwAgain) throws RemoteException {
        String result = checkUpdateNewPW(email, pw, newPw, newPwAgain);

        if("OK".equals(result)) {
            // updating the password
            boolean serverResult = client.changePassword(email,newPw);

            if(!serverResult){
                return "server failed, try again please";
            }
        }
        return result;
    }





    /**

        necessary private methods for login

     */


    // get user and check if the password is correct
    // if login successfully, register the client to the server
    private String checkLoginForSignIn(String email, String password) throws RemoteException{
        User user = client.getUser(email);

        if(user == null) {
            return "User not found";
        }
        if(!user.getPassword().equals(password)) {
            return "Incorrect password";
        }

        if(client.registerClient(user.getEmail())){
            client.setEmailOfUserLogin(user.getEmail());
        }else {
            return "You have already logged in!";
        }

        return user.getType().toString();
    }

    private String checkLoginForChangePassword(String email, String password) throws RemoteException{
        User user = client.getUser(email);

        if(user == null) {
            return "User not found";
        }
        if(!user.getPassword().equals(password)) {
            return "Incorrect password";
        }

        return "OK";
    }


    // Check if the user already exists and validate password
    private String attemptCreateUser(String email, String pw, String pwAgain, UserType userType) throws RemoteException {
        User user = client.getUser(email);

        if(user != null) {
            return "email already used";
        }

        // checking the passwords
        return validatePasswords(pw, pwAgain); // returning result of checking passwords
    }


    // method to check that two passwords are valid and matches
    private String validatePasswords(String pw, String pwAgain) {

        if(pw == null) {
            return "Password cannot be empty";
        }
        if(pw.length() < 8) {
            return "Password length must be 8 or more";
        }
        if(pw.length() > 14) {
            return "Password length must be 14 or less";
        }
        if(!pw.equals(pwAgain)) {
            return "Passwords do not match";
        }

        // verifying that the password contains at least one upper case character
        if(pw.equals(pw.toLowerCase())) {
            return "Password must contain at least one upper case letter";
        }

        // checking if there is a lower case letter in pw
        if (!pwContainsLowerCase(pw)){
            return "Password must contain at least one lower case letter";
        }

        // using regular expression to check that the password contains a number
        if(!pw.matches(".*\\d.*")) {
            return "Password must contain at least one number";
        }

        // if I reach this point, everything is okay.
        return "OK";
    }


    private boolean pwContainsLowerCase(String pw) {
        boolean foundLowerCase = false;
        for (int i = 0; i < pw.length(); i++) { // looping through all characters in the pw
            char c = pw.charAt(i);
            if(Character.isLowerCase(c)) { // checking if the character is lower case
                foundLowerCase = true;
                break;
            }
        }
        return foundLowerCase;
    }

    private String checkUpdateNewPW(String email, String pw, String newPw, String newPwAgain) throws RemoteException {

        // check that passwords are valid and matches
        if(checkLoginForChangePassword(email, pw).equals("OK")) {
            return validatePasswords(newPw, newPwAgain);
        }

        // check that username and pw is correct;
        return "Incorrect login credentials";
    }

}

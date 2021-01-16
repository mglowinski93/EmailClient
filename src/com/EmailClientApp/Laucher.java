package com.EmailClientApp;

import com.EmailClientApp.controller.persistence.PersistenceAccess;
import com.EmailClientApp.controller.persistence.ValidAccount;
import com.EmailClientApp.controller.services.LoginService;
import com.EmailClientApp.model.EmailAccount;
import com.EmailClientApp.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Laucher extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    private EmailManager emailManager = new EmailManager();

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(emailManager);
        List<ValidAccount> validAccountList = persistenceAccess.loadFromPersistence();
        if(validAccountList.size() > 0) {
            viewFactory.showMainWindow();
            for (ValidAccount validAccount: validAccountList){
                EmailAccount emailAccount = new EmailAccount(validAccount.getAddress(), validAccount.getPassword());
                LoginService loginService = new LoginService(emailAccount, emailManager);
                loginService.start();
            }
        } else {
            viewFactory.showLoginWindow();
        }
    }


    @Override
    public void stop() throws Exception {
        List<ValidAccount> validAccountList = new ArrayList<>();
        for (EmailAccount emailAccount: emailManager.getEmailAccounts()){
            validAccountList.add(new ValidAccount(emailAccount.getAddress(), emailAccount.getPassword()));
        }
        persistenceAccess.saveToPersistence(validAccountList);
    }
}
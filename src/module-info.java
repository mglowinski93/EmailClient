module EmailClient {
    requires javafx.fxml;
    requires javafx.controls;
    requires  javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail;

    opens com.EmailClientApp;
    opens com.EmailClientApp.view;
    opens com.EmailClientApp.controller;
    opens com.EmailClientApp.model;
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Oscar Lovato
 */
public abstract class LoginScene {
    public VBox content(){
        Label userLabel=new Label("USER: ");
        TextField user=new TextField();
        HBox userLayout=new HBox(10);
        userLayout.setPadding(new Insets(0,10,0,0));
        userLayout.setAlignment(Pos.CENTER_RIGHT);
        userLayout.getChildren().addAll(userLabel,user);
        
        Label passwordLabel=new Label("PASSWORD: ");
        PasswordField password=new PasswordField();
        HBox passwordLayout=new HBox(10);
        passwordLayout.setPadding(new Insets(0,10,0,0));
        passwordLayout.setAlignment(Pos.CENTER_RIGHT);
        passwordLayout.getChildren().addAll(passwordLabel,password);
        
        VBox loginLayout= new VBox(10);
        loginLayout.setPadding(new Insets(10, 0, 0, 10));
        
        Button loginButton=new Button("INICIAR");
        
        loginButton.setOnAction(event ->{onLogin(user.getText(),password.getText());
        
        });
        
        loginLayout.getChildren().addAll(userLayout,passwordLayout,loginButton);
        loginLayout.setAlignment(Pos.CENTER);
        
        return loginLayout;
    }
    
    public abstract void onLogin(String user, String password);
}

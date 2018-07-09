/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repaso;

import DAO.UsuarioDAO;
import Scenes.AdminEstudiante;
import Scenes.LoginScene;
import entidades.Usuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Oscar Lovato
 */
public class Repaso extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("LOGIN");
        
        LoginScene loginCont=new LoginScene() {
            @Override
            public void onLogin(String user, String password) {
                Usuario usuario=new Usuario();
                usuario.setUser(user);
                usuario.setPassword(password);
                UsuarioDAO controlador=new UsuarioDAO();
                
                try{
                    controlador.login(usuario);
                }catch(Exception ex){
                    System.out.println("La contraseña o usuario no coinciden");
                }
            }
        };
        AdminEstudiante estu=new AdminEstudiante();
        Scene loginScene=new Scene(estu.content(), 600, 600);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    
}

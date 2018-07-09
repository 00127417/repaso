/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Oscar Lovato
 */
public class Connection {
    public static Connection instance;
    public static String user="root";
    public static String password="";
    public static String url="jdbc:mysql://localhost:3306/estudiantes";
    public static String driver="com.mysql.jdbc.Driver";
    
    private Connection(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Connection getInstance(){
        if(instance==null){
            synchronized(Connection.class){
                if(instance==null){
                    instance=new Connection();
                }
            }
            
        }
        return instance;
    }
    
    
    
    public java.sql.Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}

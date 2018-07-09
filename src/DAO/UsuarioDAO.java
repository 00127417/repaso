/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Oscar Lovato
 */
public class UsuarioDAO extends BaseDAO<Usuario>{
    
    public UsuarioDAO(){
        table=  new TableData("usuario", "id",
        new String[]{"user"
                ,"password"});
    }
    public void login(Usuario user){
        List<Usuario> loged=findBy(user,"login");
        if(loged.size()==1){
            user.setId(loged.get(0).getId());
        }
    }

    @Override
    Usuario mapToObject(ResultSet resultset) {
        Usuario user=new Usuario();
        
        try {
            user.setId(resultset.getInt(table.PRIMARY_KEY));
            user.setUser(resultset.getString(table.fields[0]));
            user.setPassword(resultset.getString(table.fields[2]));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    PreparedStatement selectStatement(java.sql.Connection con, Usuario find, String by) {
        String query="SELECT * FROM "+ table.TABLE_NAME;
        
        PreparedStatement preparedStatement=null;
        
        try{
            if(table.fields[0].equals(by)){
                query+=" WHERE "+ table.fields[0] +" = ?";
                preparedStatement=con.prepareStatement(query);
                preparedStatement.setInt(1, find.getId());
            }else if(by.equals("login")){
                
                query+=" WHERE "+ table.fields[0]+" = ? AND "+ table.fields[1] + " = ?";
                preparedStatement=con.prepareStatement(query);
                preparedStatement.setInt(1, find.getId());
                preparedStatement.setString(2, find.getPassword());
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement insertStatement(Connection con, Usuario insertObject) {
        return null;
    }

    @Override
    PreparedStatement deleteStatement(Connection con, Usuario deleteObject) {
        return null;
    }

    @Override
    PreparedStatement updateStatement(Connection con, Usuario updateObject) {
        return null;
    }
    
}

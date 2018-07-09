/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Oscar Lovato
 */
public class EstudiantesDAO extends BaseDAO<Estudiante>{
    
    public EstudiantesDAO(){
        table=new TableData(
                "estudiantes",
                "_id",
                new String[]{"nombre","apellido","fecha_nacimiento"});
    }
    
    @Override
    Estudiante mapToObject(ResultSet resultset) {
        Estudiante e=new Estudiante();
        try{
            e.setId(resultset.getInt(table.PRIMARY_KEY));
            e.setNombre(resultset.getString(table.fields[0]));
            e.setApellido(resultset.getString(table.fields[1]));
            e.setFechaNacimiento(resultset.getDate(table.fields[2]));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    PreparedStatement selectStatement(Connection con, Estudiante find, String by) {
        String query="SELECT * FROM "+ table.TABLE_NAME +" WHERE " + by + "=?";
        PreparedStatement preparedStatement=null;
        try{
            preparedStatement=con.prepareStatement(query);
            if(by.equals(table.PRIMARY_KEY)){
                preparedStatement.setInt(1, find.getId());
            }else if(by.equals(table.fields[0])){
                preparedStatement.setString(1, "%"+find.getNombre()+ "%");
            }else if(by.equals(table.fields[1])){
                preparedStatement.setString(1, "%"+find.getApellido()+"%");
            }else if(by.equals(table.fields[2])){
                preparedStatement.setDate(1, getDate(find.getFechaNacimiento()));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement insertStatement(Connection con, Estudiante insertObject) {
        PreparedStatement preparedStatement =null;
        
        try{
            preparedStatement=con.prepareStatement("INSERT INTO " + table.TABLE_NAME + "("
                + table.fields[0] + "," + table.fields[1]
                + "," + table.fields[2] + ")" +" VALUES(?,?,?) ");
            
            preparedStatement.setString(1, insertObject.getNombre());
            preparedStatement.setString(2, insertObject.getApellido());
            preparedStatement.setDate(3, getDate(insertObject.getFechaNacimiento()));
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement deleteStatement(Connection con, Estudiante deleteObject) {
        PreparedStatement preparedStatement=null;
        try{
            preparedStatement=con.prepareStatement("DELETE FROM "+ table.TABLE_NAME + " WHERE " + table.PRIMARY_KEY
        + " = ?");
            preparedStatement.setInt(1, deleteObject.getId());
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement updateStatement(Connection con, Estudiante updateObject) {
        return null;
    }
    
}

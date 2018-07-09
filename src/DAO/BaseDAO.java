/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Oscar Lovato
 */
public abstract class BaseDAO<type> implements DAO<type>{
    
    protected TableData table;
    
    public BaseDAO(){
        
    }
    
    public BaseDAO(TableData table){
        this.table=table;
    }
    
    private void close(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Date getDate(java.util.Date date){
        return new Date(date.getTime());
    }
    abstract type mapToObject(ResultSet resultset);
    
    abstract PreparedStatement selectStatement(Connection con,type find, String by);
    
    abstract PreparedStatement insertStatement(Connection con, type insertObject);
    
    abstract PreparedStatement deleteStatement(Connection con, type deleteObject);
    
    abstract PreparedStatement updateStatement(Connection con, type updateObject);

    @Override
    public  boolean delete(type deleteObject){
        Connection con=null;
        boolean delete=false;
        try{
            con=this.con.getConnection();
            PreparedStatement preparedStatement=deleteStatement(con, deleteObject);
            if(preparedStatement.executeUpdate()>0){
                delete=true;
            }
            preparedStatement.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            close(con);
        }
        return delete;
    };

    @Override
    public boolean update(type updateObject){
        Connection con=null;
        boolean update=false;
        try{
            con=this.con.getConnection();
            PreparedStatement preparedStatement=updateStatement(con, updateObject);
            if(preparedStatement.executeUpdate()>0){
                update=true;
            }
            preparedStatement.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            close(con);
        }
        return update;
    };

    @Override
    public boolean insert(type insertObject){
        Connection con=null;
        boolean insert=false;
        try{
            con=this.con.getConnection();
            PreparedStatement preparedStatement=insertStatement(con, insertObject);
            if(preparedStatement.executeUpdate()>0){
                insert=true;
            }
            preparedStatement.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            close(con);
        }
        return insert;
    };

    @Override
    public List<type> findBy(type find, String by){
        Connection con=null;
        ArrayList<type> list=new ArrayList<>();
        
        try{
            con=this.con.getConnection();
            PreparedStatement preparedStatement = selectStatement(con, find, by);
            ResultSet resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()){
                type row=mapToObject(resultSet);
                list.add(row);
            }
            resultSet.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            close(con);
        }
        return list;
    };

    @Override
    public List<type> finAll(){
        Connection con=null;
        ArrayList<type> list=new ArrayList<>();
        try {
            con=this.con.getConnection();
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM " + table.TABLE_NAME);
            
            while(resultSet.next()){
                type row=mapToObject(resultSet);
                list.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close(con);
        }
        return list;
    };
    
    
    
    class TableData{
        final String TABLE_NAME;
        final String PRIMARY_KEY;
        final String fields[];
        
        public TableData(String table_name, String primary_key, String fields[]){
            this.TABLE_NAME=table_name;
            this.PRIMARY_KEY=primary_key;
            this.fields=fields;
        }
        
    }
}

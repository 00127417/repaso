/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.Connection;
import java.util.List;

/**
 *
 * @author Oscar Lovato
 */
public interface DAO<type> {
    Connection con= Connection.getInstance();
    
    List<type> finAll();
    
    List<type> findBy(type find,String by);
    
    boolean insert(type insertObject);
    
    boolean update(type updateObject);
    
    boolean delete(type deleteObject);
    
}

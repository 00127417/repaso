/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repaso;

import entidades.Estudiante;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Oscar Lovato
 */
public class EstudianteModelo {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellido;

    public EstudianteModelo(int id, String nombre, String apellido) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
    }
    
    public EstudianteModelo(Estudiante e){
        this.id=new SimpleIntegerProperty(e.getId());
        this.nombre=new SimpleStringProperty(e.getNombre());
        this.apellido=new SimpleStringProperty(e.getApellido());
    }
    
    public static List<EstudianteModelo> toModelo(List<Estudiante> estudiantes){
        List<EstudianteModelo> estudiantesModelo=new ArrayList<>();
        
        for(Estudiante e : estudiantes){
            estudiantesModelo.add(new EstudianteModelo(e));
        }
        
        return estudiantesModelo;
    }

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }
    
    public void setId(int id){
        this.id.set(id);
    }
    
    public int getId(){
        return id.get();
    }
    public SimpleStringProperty getNombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }
    
    public String getNombre(){
        return nombre.get();
    }
    public SimpleStringProperty getApellidoProperty() {
        return apellido;
    }
    
    public void setApellido(String Apellido){
        this.apellido.set(Apellido);
    }
    
    public String getApellido(){
        return apellido.get();
    }
}

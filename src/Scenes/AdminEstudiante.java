/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import DAO.EstudiantesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import repaso.EstudianteModelo;

/**
 *
 * @author Oscar Lovato
 */
public class AdminEstudiante {
    public VBox content(){
        EstudiantesDAO estudiantesDao=new EstudiantesDAO();
         final ObservableList<EstudianteModelo> data
                 =FXCollections.observableArrayList(EstudianteModelo.toModelo(estudiantesDao.finAll()));
         
         Label etiqueta=new Label("ADMINISTRADOR ESTUDIANTES");
         etiqueta.setFont(new Font("Arial",20));
         TableView table=new TableView();
         
         TableColumn id=new TableColumn("ID");
         id.setCellValueFactory(
                new PropertyValueFactory<EstudianteModelo, Integer>("id"));
        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(
                new PropertyValueFactory<EstudianteModelo, String>("nombre"));
        TableColumn apellido = new TableColumn("Apellido");
        apellido.setCellValueFactory(
                new PropertyValueFactory<EstudianteModelo, String>("apellido"));

        table.setItems(data);

        table.getColumns().addAll(id, nombre, apellido);

        VBox administrador = new VBox(5);
        administrador.getChildren().addAll(etiqueta, table);
        return  administrador;
    }
    
}

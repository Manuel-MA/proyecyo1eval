/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojos.POJOOfertas;
import java.sql.Date;
import mysql.mysql;

/**
 *
 * @author Manu
 */
public class DAOHaceEdicion {
    public DAOHaceEdicion(){
        
    }
    static boolean a;
    public static boolean editaUsuario(POJOOfertas pojo){
       String titulo= pojo.getTitulo();
       String contenido= pojo.getContenido();
       Date fecha = pojo.getFecha();
       int id = pojo.getId();
       String etiqueta = pojo.getEtiquetas();
        try{
          mysql.updateOne(id, "documentos", titulo, contenido, fecha, etiqueta);
          a = true;
        }catch(Exception e){
            
        }        
        return a;
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.servlet.http.HttpSession;
import mysql.mysql;
import Pojos.POJOOfertas;
import java.sql.Date;
import java.sql.ResultSet;
import org.apache.catalina.Session;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

/**
 *
 * @author Manu
 */
public class DAONuevo {
    public DAONuevo(){
        
    }
    static boolean a;
    public static boolean existeOferta(POJOOfertas pojo){
        
        String titulo = pojo.getTitulo();
        String cont = pojo.getContenido();
        String etiqueta = pojo.getEtiquetas();
        int id = pojo.getId_usuario();
        try{
          mysql conec = new mysql();
            conec.Conexion();
            String consulta = ("select titulo from documentos where titulo ='"+titulo+"' "
                    + "and contenido='"+cont+"' and etiquetas='"+etiqueta+"' and id_usuario='"+id+"'");
            System.out.println(consulta);
            ResultSet result = conec.Get(consulta);
            if(result.next()){
                a = true;
            }else{
                a = false;
            }
        }catch(Exception e){
            
        }        
        return a;
    }
    
    public static void nuevoUsuario(POJOOfertas pojo){
       String titulo= pojo.getTitulo();
       String contenido= pojo.getContenido();
       Date fecha = pojo.getFecha();
       int id_usu = pojo.getId_usuario();
       int nota = pojo.getNota();
       String etiqueta = pojo.getEtiquetas();
        try{
          mysql.insertOne(titulo, contenido, fecha, nota, id_usu, etiqueta, "1");
         
        }catch(Exception e){
            
        }        
        
        
    }
    
}

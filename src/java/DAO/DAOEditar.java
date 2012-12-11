/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojos.POJOOfertas;
import java.sql.ResultSet;
import mysql.mysql;

/**
 *
 * @author Manu
 */
public class DAOEditar {

    public DAOEditar() {
    }

    public static POJOOfertas editarRellenaOfer(POJOOfertas pojo2) {
        POJOOfertas pojo = new POJOOfertas();
        try {
            mysql conec = new mysql();
            conec.Conexion();
            String consulta = ("select * from documentos where id =" + pojo2.getId() + "");
            System.out.println(consulta);
            ResultSet result = conec.Get(consulta);

            if (result.next()) {
                pojo.setContenido(result.getString("contenido"));
                pojo.setEtiquetas(result.getString("etiquetas"));
                pojo.setFecha(result.getDate("fecha"));
                pojo.setTitulo(result.getString("titulo"));
                pojo.setId(result.getInt("id"));
            }

//            System.out.println("holaaaaaaaa"+pojo.getTitulo());
        } catch (Exception e) {
        }
        return pojo;
    }
}

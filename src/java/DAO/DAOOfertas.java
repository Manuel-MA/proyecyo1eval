/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojos.POJOOfertas;
import Pojos.POJOUsuario;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import mysql.mysql;

/**
 *
 * @author Manu
 */
public class DAOOfertas {
    public DAOOfertas() {
    }

    public static LinkedList<POJOOfertas> sacaDocu(POJOOfertas pojo2, int numpag, int limite, List order, List where) {
        LinkedList<POJOOfertas> listaOfertas = new LinkedList<>();
        try {
            mysql conec = new mysql();
            conec.Conexion();

            ResultSet result = mysql.getPage("documentos", where, order, numpag, limite);

            while (result.next()) {
                POJOOfertas pojo = new POJOOfertas();
                pojo.setContenido(result.getString("contenido"));
                pojo.setEtiquetas(result.getString("etiquetas"));
                pojo.setFecha(result.getDate("fecha"));
                pojo.setId(result.getInt("id"));
                pojo.setId_usuario(result.getInt("id_usuario"));
                pojo.setNota(result.getInt("nota"));
                pojo.setPrivado(result.getBoolean("privado"));
                pojo.setTitulo(result.getString("titulo"));
                listaOfertas.add(pojo);
            }

//            System.out.println("holaaaaaaaa"+pojo.getTitulo());
        } catch (Exception e) {
        }
        return listaOfertas;
    }
}

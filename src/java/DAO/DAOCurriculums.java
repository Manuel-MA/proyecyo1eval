/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojos.POJOCurriculums;
import Pojos.POJOOfertas;
import Pojos.POJOUsuario;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import mysql.mysql;

/**
 *
 * @author vesprada
 */
public class DAOCurriculums {

    public DAOCurriculums() {
    }

    public static LinkedList<POJOUsuario> sacaDocu(POJOCurriculums pojo2, int numpag, int limite, List order, List where) {
        LinkedList<POJOUsuario> listaCurriculums = new LinkedList<>();

        try {
            mysql conec = new mysql();
            conec.Conexion();
            String consulta = ("SELECT nombre, ape1, ape2, id from usuario where id in (SELECT id_usuario FROM DOCUMENTOS WHERE id in "
                    + "(SELECT ID_CURRICULUM FROM BOLSA WHERE ID_OFERTA =" + pojo2.getIdOferta() + "))");

            ResultSet result = conec.getPage("usuario", where, order, numpag, limite);

            while (result.next()) {
                POJOUsuario pojo = new POJOUsuario();
                pojo.setId(result.getInt("id"));
                pojo.setNombre(result.getString("nombre"));
                pojo.setApe1(result.getString("ape1"));
                pojo.setApe2(result.getString("ape2"));
                pojo.setTelefono(result.getString("telefono"));

                listaCurriculums.add(pojo);
            }

//            System.out.println("holaaaaaaaa"+pojo.getTitulo());
        } catch (Exception e) {
        }
        return listaCurriculums;
    }

    public static int sacaId(int idUsu, int idOfer) {
        int idCurr = 0;
        
        try {
            mysql conec = new mysql();
            conec.Conexion();
            String consulta = ("SELECT id FROM documentos WHERE id IN (SELECT id_curriculum FROM bolsa "
                    + "WHERE id_oferta ="+idOfer+" AND id_curriculum IN ( SELECT id FROM documentos "
                    + "WHERE id_usuario ="+idUsu+"))");

            ResultSet result = conec.Get(consulta);

            if (result.next()) {

                idCurr = result.getInt("id");

            }

//            System.out.println("holaaaaaaaa"+pojo.getTitulo());
        } catch (Exception e) {
        }
        return idCurr;
    }
    
    public static String sacaCont(int idCurr) {
        String cont = "";
        
        try {
            mysql conec = new mysql();
            conec.Conexion();
            String consulta = ("SELECT contenido FROM documentos WHERE id =" +idCurr+"");

            ResultSet result = conec.Get(consulta);

            if (result.next()) {

                cont = result.getString("contenido");

            }

//            System.out.println("holaaaaaaaa"+pojo.getTitulo());
        } catch (Exception e) {
        }
        return cont;
    }
}

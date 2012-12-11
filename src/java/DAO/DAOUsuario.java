/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojos.POJOUsuario;
import java.sql.Connection;
import java.sql.ResultSet;
import mysql.mysql;

/**
 *
 * @author vesprada
 */
public class DAOUsuario {

    public DAOUsuario() {
    
    }
    public POJOUsuario validaUsuario(POJOUsuario pojo) {
        try{ 
            mysql conec = new mysql();
            Connection a = conec.Conexion();
            String consulta = ("select * from usuario where login ='"+pojo.getUser()+
                    "' AND password = '"+pojo.getPass()+"' AND id_tipo_usuario='3'");
            ResultSet result = conec.Get(consulta);
            if(result.next()){
                pojo.setApe1(result.getString("ape1"));
                pojo.setApe2(result.getString("ape2"));
                pojo.setEmail(result.getString("email"));
                pojo.setId(result.getInt("id"));
                pojo.setNombre(result.getString("nombre"));
                pojo.setPass(result.getString("password"));
                pojo.setTipiUsuario(result.getInt("tipo_usuario"));
                pojo.setUser(result.getString("login")); 
                pojo.setTelefono(result.getString("telefono"));
                           
            }
            conec.Desconexion(a);
        }catch(Exception e){
            
        }
        return pojo;
    }

}


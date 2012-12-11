/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import mysql.mysql;
import Pojos.POJOUsuario;
/**
 *
 * @author Manu
 */
public class DAOBorrar {
    public DAOBorrar(){
        
    }
    static boolean a;
    public static boolean borraUsuario(String id){
        
        try{
          a =  mysql.deleteOne(id);
        }catch(Exception e){
            
        }
        
        return a;
        
    }
    
}

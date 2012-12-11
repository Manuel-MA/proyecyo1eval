/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author vesprada
 */
public class POJOUsuario {
    private String user;
    private String pass;
    private String email;
    private String nombre;
    private String ape1;
    private String ape2;
    private String telefono;
    private int tipiUsuario;
    private int id;
    
 
    public POJOUsuario() {
 
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    public String getPass() {
        return pass;
    }
 
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ape1
     */
    public String getApe1() {
        return ape1;
    }

    /**
     * @param ape1 the ape1 to set
     */
    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    /**
     * @return the ape2
     */
    public String getApe2() {
        return ape2;
    }

    /**
     * @param ape2 the ape2 to set
     */
    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the tipiUsuario
     */
    public int getTipiUsuario() {
        return tipiUsuario;
    }

    /**
     * @param tipiUsuario the tipiUsuario to set
     */
    public void setTipiUsuario(int tipiUsuario) {
        this.tipiUsuario = tipiUsuario;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}

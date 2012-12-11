/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.sql.Date;

/**
 *
 * @author Manu
 */
public class POJOCurriculums {
    public POJOCurriculums(){
    }
    
    private int id;
    private String idOferta;
    private String idCurriculum;
    private Date fecha;

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

    /**
     * @return the idOferta
     */
    public String getIdOferta() {
        return idOferta;
    }

    /**
     * @param idOferta the idOferta to set
     */
    public void setIdOferta(String idOferta) {
        this.idOferta = idOferta;
    }

    /**
     * @return the idCurriculum
     */
    public String getIdCurriculum() {
        return idCurriculum;
    }

    /**
     * @param idCurriculum the idCurriculum to set
     */
    public void setIdCurriculum(String idCurriculum) {
        this.idCurriculum = idCurriculum;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}

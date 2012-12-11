/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOBorrar;
import DAO.DAOCurriculums;
import DAO.DAONuevo;
import Pojos.POJOOfertas;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import DAO.DAOEditar;
import DAO.DAOHaceEdicion;
import DAO.DAOOfertas;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import net.ausiasmarch.common.*;

/**
 *
 * @author vesprada
 */
public class EditarBorrar extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String boton = request.getParameter("boton");
        String destino1 = "";
        switch (boton) {
            case "Nuevo":
                destino1 = "/nuevoUsuario.jsp";
                break;
            case "VerCurriculum":
                String titulo = request.getParameter("titulo");
                String pagCurr = request.getParameter("pagCurr");
                String id1 = request.getParameter("idUsu");
                String idOfer1 = request.getParameter("idOfer");
                int id = Integer.parseInt(id1);
                int idOfer = Integer.parseInt(idOfer1);
                String nombre1 = request.getParameter("nombre");
                String ap1 = request.getParameter("ap1");
                String ap2 = request.getParameter("ap2");
                String telf = request.getParameter("telf");
                String ordenacionCurr = request.getParameter("ordenacionCurr");
                String nombre = nombre1+" "+ap1+" "+ap2;
                System.out.println(id+ nombre1+telf+idOfer);
                int idCurr = DAOCurriculums.sacaId(id, idOfer);
                System.out.println(idCurr);
                String cont = DAOCurriculums.sacaCont(idCurr);
                System.out.println(cont);
                request.setAttribute("nombre", nombre);
                request.setAttribute("nombre1", nombre1);
                request.setAttribute("telf", telf);
                request.setAttribute("cont", cont);
                request.setAttribute("idOfer", idOfer);
                request.setAttribute("ordenacionCurr", ordenacionCurr);
                request.setAttribute("pagCurr", pagCurr);
                request.setAttribute("titulo", titulo);
                destino1 = "/verCurriculum.jsp";
        }

        ServletContext conte = getServletConfig().getServletContext();
        RequestDispatcher reqDispatcher = conte.getRequestDispatcher(destino1);
        reqDispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            /*Recuperamos los Parametros necesarios y los convertimos*/
            
            String boton = request.getParameter("boton");
            String id1 = request.getParameter("id");
            String titulo = request.getParameter("titulo");
            String cont = request.getParameter("contenido");
            int id = 0;
            String editaFecha = request.getParameter("fecha");
            
            if (id1 != null){
            id = Integer.parseInt(id1);
            }
            String etiqueta = request.getParameter("etiqueta");

            /*
             * Creamos el objeto String destino para redirigirnos después.
             * Se crea también el pojo de ofertas que utilizaremos para Editar y Crear una nueva oferta.
             */

            String destino = "";
            POJOOfertas pojoO = new POJOOfertas();
            switch (boton) {
                /*Borra oferta*/
                case "Borrar":

                    DAOBorrar.borraUsuario(id1);
                    destino = "/ConexionBase";
                    break;
                /*Rellena campos para edición de ofertas*/
                case "Editar":

                    int idO = Integer.parseInt(id1);
                    pojoO.setId(idO);
                    pojoO = DAOEditar.editarRellenaOfer(pojoO);

                    if (!pojoO.getTitulo().equals("")) {

                        request.setAttribute("titulo", pojoO.getTitulo());
                        request.setAttribute("cont", pojoO.getContenido());
                        request.setAttribute("fecha", pojoO.getFecha());
                        request.setAttribute("etiqueta", pojoO.getEtiquetas());
                        request.setAttribute("id", pojoO.getId());

                        destino = "/editar.jsp";
                    }
                    break;
                /*Edita oferta*/
                case "Confirmar":
                    
                   
                    Date fecha1 = StringFecha1(editaFecha);
                    pojoO.setTitulo(titulo);
                    pojoO.setContenido(cont);
                    pojoO.setFecha(fecha1);
                    pojoO.setEtiquetas(etiqueta);
                    pojoO.setId(id);

                    DAOHaceEdicion.editaUsuario(pojoO);

                    destino = "/ConexionBase";

                    break;
                /*Crea oferta*/
                case "Aceptar":
                    Calendar cal1 = Calendar.getInstance();
                    String nota2 = request.getParameter("nota");
                    String fecha = cal1.get(Calendar.DAY_OF_MONTH) + "-" + cal1.get(Calendar.MONTH) + "-" + cal1.get(Calendar.YEAR);
                    Date fecha2 = StringFecha(fecha);
                    
                    int nota = Integer.parseInt(nota2);
                    pojoO.setTitulo(titulo);
                    pojoO.setContenido(cont);
                    pojoO.setId_usuario(id);
                    pojoO.setFecha(fecha2);
                    pojoO.setEtiquetas(etiqueta);
                    pojoO.setNota(nota);
                    
                    boolean existe = DAONuevo.existeOferta(pojoO);
                    if(!existe){
                        DAONuevo.nuevoUsuario(pojoO);
                    }
                    //DAONuevo.nuevoUsuario(pojoO);

                    destino = "/ConexionBase";
            }

            ServletContext conte = getServletConfig().getServletContext();
            RequestDispatcher reqDispatcher = conte.getRequestDispatcher(destino);
            reqDispatcher.forward(request, response);

//            processRequest(request, response);
        } catch (Exception ex) {
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /* Método para convertir de String a sql.Date*/
    public static Date StringFecha(String fecha) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf.parse(fecha);
        java.sql.Date sqlDate = new Date(date.getTime());

        return sqlDate;
    }

    public static Date StringFecha1(String fecha) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(fecha);
        java.sql.Date sqlDate = new Date(date.getTime());

        return sqlDate;
    }
}

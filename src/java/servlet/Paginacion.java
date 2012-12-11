/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOCurriculums;
import DAO.DAOOfertas;
import DAO.DAOUsuario;
import Pojos.POJOCurriculums;
import Pojos.POJOOfertas;
import Pojos.POJOUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mysql.mysql;

/**
 *
 * @author vesprada
 */
public class Paginacion extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Paginacion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Paginacion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
        processRequest(request, response);
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
        HttpSession Session = request.getSession();
        Object user1 = Session.getAttribute("user");
        String tipo = request.getParameter("tipo");
        String idOfer = request.getParameter("idOfer");
        String titulo = request.getParameter("nombre");
        String pagC = request.getParameter("pagina");
        String accion = request.getParameter("pag");
        Object pagActualOfer = Session.getAttribute("numPagOfer");
        Object pagActualCurr = Session.getAttribute("numPagCurr");
        
        Object ordenacionOfer = Session.getAttribute("ordenacionOfer");
        String ordenacionCurr = request.getParameter("ordenacionCurr");
        String ordC = "id";

        if (user1 != null) {
            POJOOfertas pojoO = new POJOOfertas();
            POJOUsuario pojoU = new POJOUsuario();
            POJOCurriculums pojoC = new POJOCurriculums();
            DAOUsuario daoU = new DAOUsuario();
            ArrayList order = new ArrayList();
            ArrayList orderCurr = new ArrayList();
            ArrayList where = new ArrayList();
            String ordenacion = "";
            String ordO = ordenacionOfer.toString();
            int rpp = 2;
            int pa = 1;
            
            
            
            if (tipo.equals("ofertas")) {
                pa = Integer.parseInt(pagActualOfer.toString());
                ordenacion = "fecha";
                if(ordO!=null){
                    ordenacion = ordO;
                }else{                    
                order.add(ordenacion);
                }
                order.add(ordenacion);
            } else {
                pa = Integer.parseInt(pagC);
                ordenacion = "id";
                if(ordenacionCurr!=null){
                    ordenacion = ordenacionCurr;
                }else{ 
                orderCurr.add(ordenacion);
                }
                orderCurr.add(ordenacion);
            }
            


            String destino = null;
            try {
                String user = (String) Session.getAttribute("user");
                String pass = (String) Session.getAttribute("pass");

                pojoU.setUser(user);
                pojoU.setPass(pass);

                pojoU = daoU.validaUsuario(pojoU);


                int id = pojoU.getId();
                pojoO.setId_usuario(id);
                pojoC.setIdOferta(idOfer);
                String pa1 = request.getParameter("pagina");
                int pa2 = Integer.parseInt(pa1);
                int pagDest = 0;
                String pagDes = request.getParameter("pagDestino");
                if (tipo.equals("ofertas")) {
                    where.add("id_usuario=" + pojoU.getId());
                } else {
                    where.add("id in (SELECT id_usuario FROM DOCUMENTOS WHERE id in "
                            + "(SELECT ID_CURRICULUM FROM BOLSA WHERE ID_OFERTA =" + idOfer + "))");
                }


                /*
                 * Se obtiene la última página con respecto a los registros por página, a los
                 * resultados que devuelva la base de datos y al patrón de ordenación.
                 */
                int ultima = 0;
                if (tipo.equals("ofertas")) {
                ultima = mysql.getPages("documentos", order, where, rpp);
                }else{
                ultima = mysql.getPages("usuario", orderCurr, where, rpp);
                }
               
                switch (accion) {


                    /*
                     * En caso de que se le dé al botón "Primera" te manda a la primera página.
                     */
                    case "Primera":
                        pa = 1;
                        break;


                    /*
                     * En el caso de que se le dé al botón "Anterior" te llevará la página actual - 1
                     * Si la página solicitada es igual a 0, te mantendrás en la página actual.
                     */
                    case "Anterior":
                        pa = pa2 - 1;
                        if (pa == 0) {
                            pa = 1;
                        }
                        break;



                    /*
                     * En el caso de que se le dé al botón "Siguiente" te llevará la página actual + 1
                     * Si es mayor al número total de páginas te mantendrás en la página actual.
                     */
                    case "Siguiente":
                        pa = pa2 + 1;
                        if(pa > ultima){
                            pa = pa2;
                        }
                        if (ultima == 0) {
                            pa = 1;
                        } 
                        
                        break;


                    /*
                     * La página destino es la última
                     */
                    case "Ultima":
                        if (ultima == 0) {
                            pa = 1;
                        } else {
                            pa = ultima;
                        }
                        break;


                    /*
                     * En caso de que sea manual la elección de la página te mantendrá en la página actual
                     * si la página solicitada es mayor o menor que la última y primera página respectivamente.
                     */
                    case "Ir":
                        /*
                         * En caso de que no sea un tipo int la página insertada, el destino que se recoge
                         * es la página actual.
                         */
                        if (!pagDes.equals("") && isNumeric(pagDes)) {
                            pagDest = Integer.parseInt(pagDes);
                        } else {
                            pa = pa2;
                        }
                        pa = pagDest;
                        if (pa > ultima) {
                            pa = pa2;
                        }
                        if (pa < 1) {
                            pa = pa2;
                        }
                }

                if (tipo.equals("ofertas")) {
                    LinkedList<POJOOfertas> lista = DAOOfertas.sacaDocu(pojoO, pa, rpp, order, where);
                    request.setAttribute("ofertas", lista);
                    Session.setAttribute("numPagOfer", pa);
                    request.setAttribute("pagOfertas", pagActualOfer);
                    request.setAttribute("ordenacion", ordenacion);
                    
                    destino = "/bienvenido.jsp";
                } else {
                    LinkedList<POJOUsuario> lista = DAOCurriculums.sacaDocu(pojoC, pa, rpp, orderCurr, where);
                    request.setAttribute("curr", lista);
                    request.setAttribute("pagina", pa);
                    request.setAttribute("idOfer", idOfer);
                    request.setAttribute("titulo", titulo);
                    request.setAttribute("ordenacion", ordenacion);
                    request.setAttribute("ordenacionCurr", ordenacionCurr);
                    destino = "/curriculums.jsp";
                }


                ServletContext cont = getServletConfig().getServletContext();
                RequestDispatcher reqDispatcher = cont.getRequestDispatcher(destino);
                reqDispatcher.forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
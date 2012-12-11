/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOOfertas;
import DAO.DAOUsuario;
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
import org.apache.catalina.Session;

/**
 *
 * @author vesprada
 */
public class ConexionBase extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.sendRedirect("index.jsp");

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        HttpSession session = request.getSession();
        POJOOfertas pojoO = new POJOOfertas();
        POJOUsuario pojoU = new POJOUsuario();
        DAOUsuario daoU = new DAOUsuario();
        String ordenacion = "";
        ArrayList order = new ArrayList();
        ArrayList where = new ArrayList();
        String orden = request.getParameter("campo");
        String tipo = request.getParameter("orden");
        Object ordenPag = session.getAttribute("ordenacionOfer");
        if(ordenPag == null){
            ordenacion = "fecha";
        }
        else{
             String ordenPagg = ordenPag.toString();
             ordenacion =ordenPagg;
        }
        if(orden != null && tipo != null){
            switch(orden){
                case "Nombre":
                    ordenacion = "Titulo";
                    break;
                case "Fecha":
                    ordenacion = "Fecha";
                    break;
                case "Nota":
                    ordenacion = "Nota";                     
            }
            switch(tipo){
                case"&#8593;":
                    ordenacion += " asc";
                    break;
                case"&#8595;":
                    ordenacion += " desc";
            }
        }
        
        order.add(ordenacion);

        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession();
        int rpp = 2;
        int pa = 1;
        Object pa2 = session.getAttribute("numPagOfer");
//        String pa1 = pa2.toString();
        String pagOrden = request.getParameter("pagina");
        if(pa2 != null){
            pa = Integer.parseInt(pa2.toString());
        }
//        if(pagOrden != null){
//            pa = Integer.parseInt(pagOrden);
//        }
        
        System.out.println("La página actual es:"+pa);

        String destino = null;
        try {
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");

            pojoU.setUser(user);
            pojoU.setPass(pass);

            pojoU = daoU.validaUsuario(pojoU);
            where.add("id_usuario=" + pojoU.getId());
            int id = pojoU.getId();
            pojoO.setId_usuario(id);

            if (pojoU.getId() != 0) {
                Session.setAttribute("user", pojoU.getUser());
                Session.setAttribute("pass", pojoU.getPass());
                Session.setAttribute("nombre", pojoU.getNombre());
                Session.setAttribute("ape1", pojoU.getApe1());
                Session.setAttribute("ape2", pojoU.getApe2());
                Session.setAttribute("id", pojoU.getId());
                Session.setAttribute("titulo", pojoO.getTitulo());
                Session.setAttribute("ordenacionOfer", ordenacion);
                Session.setAttribute("numPagOfer", pa);
                Session.setAttribute("ordenacionCurr", "id");
                LinkedList<POJOOfertas> lista = DAOOfertas.sacaDocu(pojoO, pa, rpp, order, where);
                request.setAttribute("ofertas", lista);
                
                
                destino = "/bienvenido.jsp";
            } else {
                String error = "Usuario y/o clave incorrectos";
                Session.setAttribute("error", error);
                destino = "/index.jsp";
            }

            ServletContext cont = getServletConfig().getServletContext();
            RequestDispatcher reqDispatcher = cont.getRequestDispatcher(destino);
            reqDispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
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
}

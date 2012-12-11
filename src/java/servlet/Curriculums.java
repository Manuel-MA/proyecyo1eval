/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DAOCurriculums;
import Pojos.POJOCurriculums;
import Pojos.POJOOfertas;
import Pojos.POJOUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

/**
 *
 * @author Manu
 */
public class Curriculums extends HttpServlet {

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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String destino = null;
        ArrayList order = new ArrayList();
        String ordenacion= "id";
        String ordenVerCurriculums = request.getParameter("ordenacionCurr");
        String pagCurrO = request.getParameter("pagina");
        String orden = request.getParameter("campo");
        String tipo = request.getParameter("orden");
        if (ordenVerCurriculums == null){
        if(orden != null && tipo != null){
            switch(orden){
                case "Nombre":
                    ordenacion = "Nombre";                    
            }
            switch(tipo){
                case"&#8593;":
                    ordenacion += " asc";
                    break;
                case"&#8595;":
                    ordenacion += " desc";
            }
        }
        }else{
            ordenacion = ordenVerCurriculums;
        }
        order.add(ordenacion);
        ArrayList where = new ArrayList();
//        String pagOrden = request.getParameter("pagina");
        String pagCurr = request.getParameter("pagCurr");
        int rpp = 2;
        int pa = 1;
        if(pagCurr != null){
            pa = Integer.parseInt(pagCurr);
        }
        if(pagCurrO != null){
            pa = Integer.parseInt(pagCurrO);
        }
    
        try {
            String titulo = request.getParameter("titulo");
            String id = request.getParameter("ofer");
            where.add("id in (SELECT id_usuario FROM DOCUMENTOS WHERE id in "
                    + "(SELECT ID_CURRICULUM FROM BOLSA WHERE ID_OFERTA ="+id+"))");
            POJOCurriculums pojoC = new POJOCurriculums();
            pojoC.setIdOferta(id);
            LinkedList<POJOUsuario> lista = DAOCurriculums.sacaDocu(pojoC, pa, rpp, order, where);
            request.setAttribute("curr", lista);
            session.setAttribute("numPagCurr", pa);
            request.setAttribute("idOfer",id);
            request.setAttribute("titulo",titulo);
            request.setAttribute("pagina",pa);
            request.setAttribute("ordenacionCurr", ordenacion);
            
            destino = "/curriculums.jsp";
            
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
         processRequest(request, response);
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

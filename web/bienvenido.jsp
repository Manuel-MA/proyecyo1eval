<%-- 
    Document   : bienvenido
    Created on : 07-nov-2012, 0:42:12
    Author     : Manu
--%>

<%@page import="sun.awt.image.ImageWatched.Link"%>
<%@page import="DAO.DAOOfertas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Pojos.POJOOfertas"%>
<%@page import="Pojos.POJOUsuario"%>
<%@page import="DAO.DAOUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    //si no hay ninguna sesión iniciada, redirecciona a la página de inicio
    Object user = sesion.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
    } else {
%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>    
        
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <link rel="stylesheet" href="cssbienvenido.css" type="text/css"/>
        <script language="javascript" type="text/javascript" src="JavaScript.js"></script>
        <title>Ofertas <%=sesion.getAttribute("user")%> </title>
    </head>
    <body onLoad="muestraReloj()">
        <div id="envoltorio">
            <span id="spanreloj"></span>
            <div id="cabecera">
                <div id="usuario">
                    <form name="formCerrar" action="Cerrar" method="post">
                        Sesión iniciada como <%=sesion.getAttribute("user")%>
                        <input type="submit" value="Cerrar sesión" />
                    </form>
                </div>

                <h1 id="titulo">MOODLE AUSIAS MARCH</h1>
            </div>
            <div id="contenedor">
                <div id="menu">aaa</div>
                <div id="contenido"><h1>Ofertas privadas de la empresa <%=sesion.getAttribute("nombre")%></h1>
                    <%
                        LinkedList<POJOOfertas> lista = (LinkedList) request.getAttribute("ofertas");
                        if(lista.size()== 0){
                    %>
                    <div id="vacio"> No hay ninguna oferta disponible</div><br/>
                    <a href="EditarBorrar?boton=Nuevo">Nueva oferta</a>
                    <%
                                            }else{
                    %> 
                    <p>Tus ofertas son las siguientes:</p>
                    <FORM name="posicion" ACTION="/BaseDatos1/Paginacion" METHOD="POST">
                        <input type = "hidden" name = "pagina" value = "<%=session.getAttribute("numPagOfer")%>" >
                        <input type = "hidden" name = "ordenacionOfer" value = "<%=request.getAttribute("ordenacionOfer")%>" >
                            <input type = "hidden" name = "tipo" value = "ofertas" >
                                <input type="submit" name="pag" value="Primera" />
                                <input type="submit" name="pag" value="Anterior" />
                                <input type="text" id="pagina" name="pagDestino" value="<%=session.getAttribute("numPagOfer")%>" onkeypress="return handleEnter(this, event)" />
                                <input type="submit" name="pag" value="Ir" />
                                <input type="submit" name="pag" value="Siguiente" />
                                <input type="submit" name="pag" value="Ultima" /></FORM>
                                <br/>
                                <a href="EditarBorrar?boton=Nuevo">Nueva oferta</a>
                                <table id="tabla1" class="tablesorter">
                                    <tr>
                                        <th>Nombre oferta
                                            <form action="/BaseDatos1/ConexionBase" METHOD="POST">
                                                <input type="hidden" name="campo" value="Nombre"/>
                                                <input type = "hidden" name = "pagina" value = "<%=request.getAttribute("numPag") %>" >
                                                <input type="hidden" name="user" value="<%=sesion.getAttribute("user")%>"/>
                                                <input type="hidden" name="pass" value="<%=sesion.getAttribute("pass")%>"/>
                                                    <input type="submit" name="orden" value="&#8593;"/>
                                                    <input type="submit" name="orden" value="&#8595;"/>
                                            </form>
                                        </th>
                                        <th>Fecha
                                        <form action="/BaseDatos1/ConexionBase" METHOD="POST">
                                            <input type="hidden" name="campo" value="Fecha"/>
                                            <input type = "hidden" name = "pagina" value = "<%=request.getAttribute("numPag") %>" >
                                            <input type="hidden" name="user" value="<%=sesion.getAttribute("user")%>"/>
                                            <input type="hidden" name="pass" value="<%=sesion.getAttribute("pass")%>"/>
                                                    <input type="submit" name="orden" value="&#8593;"/>
                                                    <input type="submit" name="orden" value="&#8595;"/>
                                        </form>
                                        </th>
                                        <th>Nota
                                        <form action="/BaseDatos1/ConexionBase" METHOD="POST">
                                            <input type="hidden" name="campo" value="Nota"/>
                                            <input type = "hidden" name = "pagina" value = "<%=request.getAttribute("numPag") %>" >
                                            <input type="hidden" name="user" value="<%=sesion.getAttribute("user")%>"/>
                                            <input type="hidden" name="pass" value="<%=sesion.getAttribute("pass")%>"/>
                                                    <input type="submit" name="orden" value="&#8593;"/>
                                                    <input type="submit" name="orden" value="&#8595;"/>
                                        </form>
                                        </th>
                                        <th>Acción</th>
                                        <%
                                            if(lista.size()== 0){
                                           %>
                                             No hay ninguna oferta disponible
                                        <%
                                            }
                                            for (int i = 0; i < lista.size(); i++) {
                                        %>
                                        <tr>
                                            <td><a href="Curriculums?ofer=<%=lista.get(i).getId()%>&titulo=<%=lista.get(i).getTitulo()%>" >
                                                    <%= lista.get(i).getTitulo()%></a></td>
                                            <td><%=lista.get(i).getFecha()%></td>
                                            <td><%=lista.get(i).getNota()%></td>
                                            <td id="accion"><FORM name="curriculum" ACTION="/BaseDatos1/EditarBorrar" METHOD="POST">
                                                <input type = "hidden" name = "user" value ="<%=sesion.getAttribute("user")%>" >
                                                <input type = "hidden" name = "pass" value = "<%= sesion.getAttribute("pass")%>" >
                                                <input type = "hidden" name = "id" value = "<%=lista.get(i).getId()%>" >
                                                <input type="submit" name="boton" value="Editar" />
                                                <input type="submit" name="boton" id="borrar" onclick="return confirmar()" value="Borrar" /></FORM>
                                            </td>
                                        </tr>
                                        <%  }%>

                                  </table>  
                                        <% }%> 
                                <br/>




                              </div>
                            </div>
                            <div id="pie">
                               <p>
                                 <a href="http://validator.w3.org/check?uri=referer"><img
                                                                                src="http://www.w3.org/Icons/valid-xhtml10"
                                                                                alt="Valid XHTML 1.0 Transitional" height="31" width="88" /></a>
                               </p>
                            </div>
                           </div>
                         </body>
                    </html>
              <%  }%>
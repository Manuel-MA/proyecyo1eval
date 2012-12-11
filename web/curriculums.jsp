<%-- 
    Document   : curriculums
    Created on : 09-nov-2012, 19:47:32
    Author     : vesprada
--%>

<%@page import="Pojos.POJOCurriculums"%>
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
        <title>Curriculums</title>
    </head>
    <body onLoad="muestraReloj()">        
        <div id="envoltorio">
            <span id="spanreloj"></span>
            <div id="cabecera"><div id="usuario">
                    <form name="formCerrar" action="Cerrar" method="post">
                        Sesión iniciada como <%=sesion.getAttribute("user")%>
                        <input type="submit" value="Cerrar sesión" />
                    </form>
                </div>
                <h1 id="titulo">MOODLE AUSIAS MARCH</h1>
            </div>
            <div id="contenedor">
                <div id="menu">aaa</div>
                <div id="contenido">
                    <h1>Curriculums disponibles de <%= request.getAttribute("titulo") %>:</h1>
                    <p>Los curriculums son los siguientes:</p>

                        <%
                        LinkedList<POJOUsuario> lista = (LinkedList) request.getAttribute("curr");
                        
                        if(lista.size()== 0){
                        %>
                        <div id="vacio"> No hay ningun curriculum disponible.</div><br/>
                        <%
                                            }else{
                        %>
                        
                        <FORM name="posicion" ACTION="/BaseDatos1/Paginacion" METHOD="POST">
                        <input type = "hidden" name = "ordenacionCurr" value = "<%=request.getAttribute("ordenacionCurr") %>" >
                        <input type = "hidden" name = "pagina" value = "<%=request.getAttribute("pagina") %>" >
                        <input type = "hidden" name = "idOfer" value = "<%=request.getAttribute("idOfer") %>" >
                        <input type = "hidden" name = "nombre" value = "<%=request.getAttribute("titulo") %>" >
                        <input type = "hidden" name = "tipo" value = "curriculums" >
                        <input type="submit" name="pag" value="Primera" />
                        <input type="submit" name="pag" value="Anterior" />
                        <input type="text" id="pagina" name="pagDestino" value=" <%=request.getAttribute("pagina")%>
                               " onkeypress="return handleEnter(this, event)" />
                        <input type="submit" name="pag" value="Ir" />
                        <input type="submit" name="pag" value="Siguiente" />
                        <input type="submit" name="pag" value="Ultima" /></FORM>
                        <br/>
                        <table border="3">
                        <tr>
                        <th>Solicitante
                        <form action="/BaseDatos1/Curriculums" METHOD="get">
                               <input type="hidden" name="campo" value="Nombre"/>
                               <input type = "hidden" name = "pagOfertas" value = "<%=request.getAttribute("pagOfertas")%>"/>
                               <input type = "hidden" name = "pagina" value = "<%=request.getAttribute("pagina") %>" >
                               <input type = "hidden" name = "ofer" value = "<%=request.getAttribute("idOfer") %>" >
                               <input type = "hidden" name = "titulo" value = "<%=request.getAttribute("titulo") %>" >
                               <input type="hidden" name="pass" value="<%=sesion.getAttribute("pass")%>"/>
                                      <input type="submit" name="orden" value="&#8593;"/>
                                      <input type="submit" name="orden" value="&#8595;"/>
                        </form>
                        </th>
                        <th>Teléfono</th>

                        <%
                        for (int i = 0; i < lista.size(); i++) {
                            String nombre = lista.get(i).getNombre() +" "
                                    + lista.get(i).getApe1()+" "+ lista.get(i).getApe2();
                        %>
                            <tr>
                            <td><a href="EditarBorrar?boton=VerCurriculum&idUsu=<%=lista.get(i).getId()%>&nombre=<%=lista.get(i).getNombre()%>&ap1=<%=lista.get(i).getApe1()%>&ap2=<%=lista.get(i).getApe2()%>&telf=<%=lista.get(i).getTelefono()%>&idOfer=<%=request.getAttribute("idOfer")%>&idDocu=<%=request.getAttribute("idDocu")%>&ordenacionCurr=<%=request.getAttribute("ordenacionCurr")%>&titulo=<%= request.getAttribute("titulo")%>&pagCurr=<%=request.getAttribute("pagina")%>"><%=nombre%></a></td>
                            <td><%=lista.get(i).getTelefono()%></td>
                                  <% }%>                
                        </table>
                   <% }%> 
                    <form name="formVolver" action="ConexionBase" method="post">
                        <input type="hidden" name="pagOfertas" value="<%= request.getAttribute("pagOfertas")%>"/>
                        <input type="hidden" name="user" value="<%= sesion.getAttribute("user")%>"/>
                        <input type="hidden" name="pass" value="<%= sesion.getAttribute("pass")%>"/>
                        <input type="submit" value="Atrás" />
                    </form>
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
<% }%>

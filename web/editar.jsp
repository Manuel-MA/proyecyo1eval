<%-- 
    Document   : editar
    Created on : 16-nov-2012, 12:35:16
    Author     : Manu
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession();
    //si no hay ninguna sesión iniciada, redirecciona a la página de inicio
    Object user = sesion.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
    } else {
        String titulo = request.getAttribute("titulo").toString();
        String contenido = request.getAttribute("cont").toString();
        String etiqueta = request.getAttribute("etiqueta").toString();
        String fecha = request.getAttribute("fecha").toString();
        String id = request.getAttribute("id").toString();
%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="cssbienvenido.css" type="text/css"/>
            <link rel="stylesheet" href="csseditar.css" type="text/css"/>
            <script language="javascript" type="text/javascript" src="JavaScript.js"></script>
            <script language="javascript" type="text/javascript" src="validacionEditar.js"></script>
            <title>Editar oferta</title>
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
                <div id="contenido">
                    <h1>Editar oferta</h1>

                    <form name="formNuevo" action="/BaseDatos1/EditarBorrar" method="post">
                        Título oferta: <input type="text" id="titulo1" name="titulo" value="<%= titulo%>"/><br/>
                        Contenido:<textarea cols="20" rows="7" name="contenido"><%= contenido%></textarea><br/>
                        Etiqueta: <input type="text" name="etiqueta" value="<%= etiqueta%>"/><br/>
                        Fecha: <input type="text" name="fecha" id="fecha" value="<%= fecha%>"/><br/>                        
                        <input type="hidden" name="nota" value="0"/>
                        <input type="hidden" name="id" value="<%=id%>"/>
                        <input type="hidden" name="user" value="<%=sesion.getAttribute("user")%>"/>
                        <input type="hidden" name="pass" value="<%=sesion.getAttribute("pass")%>"/>         
                        <input type="hidden" name="privado" value="true"/>
                        <div id="confirmar">
                            <input type="submit" name="boton" id="botonEditar" onclick="return Verifica()" value="Confirmar" />
                        </div>
                    </form>
                
                <div id="atras">
                    <form name="formVolver" action="ConexionBase" method="post">
                        <input type="hidden" name="user" value="<%= sesion.getAttribute("user")%>"/>
                        <input type="hidden" name="pass" value="<%= sesion.getAttribute("pass")%>"/>
                        <input type="submit" value="Atrás" />
                    </form>
                </div>
                <script language="javascript" type="text/javascript">
                    var nuevo = document.getElementById("titulo1"); 
                    nuevo.focus(); 
                </script>
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

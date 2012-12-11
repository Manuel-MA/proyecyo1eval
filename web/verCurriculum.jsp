<%-- 
    Document   : verCurriculum
    Created on : 22-nov-2012, 23:44:40
    Author     : Manu
--%>

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
        <script>
            
        </script>
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
                <div id="contenido"><h1>Curriculum de <%=request.getAttribute("nombre1")%></h1>
                    <b>Nombre:</b> <%= request.getAttribute("nombre")%><br/>
                    <b>Telefono:</b> <%= request.getAttribute("telf")%><br/>
                    <b>Contenido curriculum:</b> <%= request.getAttribute("cont")%>
                    <form name="formVolver" action="Curriculums" method="get">
                        <input type="hidden" name="tipo" value="curriculums"/>
                        <input type="hidden" name="titulo" value="<%= request.getAttribute("titulo")%>"/>
                        <input type="hidden" name="pagCurr" value="<%= request.getAttribute("pagCurr")%>"/>
                        <input type="hidden" name="ofer" value="<%= request.getAttribute("idOfer")%>"/>
                        <input type="hidden" name="ordenacionCurr" value="<%= request.getAttribute("ordenacionCurr")%>"/>
                        <input type="submit" value="Atrás" />

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

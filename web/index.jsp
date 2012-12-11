<%-- 
    Document   : index
    Created on : 16-oct-2012, 19:02:58
    Author     : vesprada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
    <HEAD>
        <link rel="stylesheet" href="cssbienvenido.css" type="text/css"/>
        <script language="javascript" type="text/javascript" src="JavaScript.js"></script>
        <TITLE>Autenticación</TITLE>
    </HEAD>
    <BODY onload="muestraReloj()">
        <div id="envoltorio">
            <span id="spanreloj"></span>
            <div id="cabecera">
                <div id="usuario">
                    <%
                        HttpSession sesion = request.getSession();
                        //si no hay ninguna sesión iniciada, redirecciona a la página de inicio
                        Object error = sesion.getAttribute("error");
                        Object user = sesion.getAttribute("user");
                        if (error != null) { %>
                            
                    
                    <FORM name="libros" ACTION="/BaseDatos1/ConexionBase" METHOD="POST">
                        Usuario: <INPUT TYPE="TEXT" NAME="user">
                        Contraseña <INPUT TYPE="PASSWORD" NAME="pass">
                        <INPUT TYPE="SUBMIT" NAME="Enviar" VALUE="Enviar">
                        <INPUT TYPE="RESET" NAME="botonLimpiar" VALUE="Limpiar">
                    </FORM>
                           <% out.println(sesion.getAttribute("error"));%>
                </div>

                <h1 id="titulo">MOODLE AUSIAS MARCH</h1>
            </div>

            <%
            } else if (user == null) {
            %>
            <FORM name="libros" ACTION="/BaseDatos1/ConexionBase" METHOD="POST">
                Usuario: <INPUT TYPE="TEXT" NAME="user">
                Contraseña <INPUT TYPE="PASSWORD" NAME="pass">
                <INPUT TYPE="SUBMIT" NAME="Enviar" VALUE="Enviar">
                <INPUT TYPE="RESET" NAME="botonLimpiar" VALUE="Limpiar">
            </FORM>
        </div>

        <h1 id="titulo">MOODLE AUSIAS MARCH</h1>
    </div>
    <% } else {%>
    <form name="formCerrar" action="Cerrar" method="post">
        Sesión iniciada como <%=sesion.getAttribute("user")%>
        <input type="submit" value="Cerrar sesión" />
    </form>
</div>
<h1 id="titulo">MOODLE AUSIAS MARCH</h1>
</div>

<% }%>
<div id="contenedor">
    <div id="menu">aaa</div>
    <div id="contenido">
        <h1>Página de inicio</h1>



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
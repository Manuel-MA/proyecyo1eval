<%-- 
    Document   : nuevoUsuario
    Created on : 14-nov-2012, 13:32:15
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="cssbienvenido.css" type="text/css"/>
        <link rel="stylesheet" href="cssnuevo.css" type="text/css"/>
        <script language="javascript" type="text/javascript" src="JavaScript.js"></script>
        <script language="javascript" type="text/javascript" src="validacion.js"></script>
        <title>Nuevo usuario</title>
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
                    <h1>Nueva oferta</h1>
                   
                    <form name="formNuevo" action="/BaseDatos1/EditarBorrar" method="post">
                        Título oferta: <input type="text" id="titulo1" name="titulo" value=""/><br/>
                        Contenido:<textarea cols="20" id="cont"rows="7" name="contenido">Inserta aquí el contenido de la oferta</textarea><br/>
                        Etiqueta: <input type="text" id="etiqueta" name="etiqueta" value=""/><br/>
                        <input type="hidden" name="id" value="<%=sesion.getAttribute("id")%>"/>
                        <input type="hidden" name="user" value="<%=sesion.getAttribute("user")%>"/>
                        <input type="hidden" name="pass" value="<%=sesion.getAttribute("pass")%>"/>
                        <input type="hidden" name="nota" value="0"/>
                        <input type="hidden" name="etiqueta" value="oferta"/>
                        <input type="hidden" name="privado" value="true"/>
                        <div id="aceptar">
                        <input type="submit" name="boton" OnClick="return Verifica()" value="Aceptar" />
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
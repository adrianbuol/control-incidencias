<%@page import="org.japo.java.libraries.UtilesUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web App</title>
        <link rel="stylesheet" href="public/css/usuario/usuario-login.css"/>
        <link rel="stylesheet" href="public/css/general/general.css"/>
    </head>
    <body>
        <div id="container">
            <h2>Login del Usuario</h2>

            <form action="?cmd=usuario-login&op=proceso" 
                  method="POST"
                  accept-charset="ISO-8859-1">
                <div class="fieldset">
                    <label for="user">Nombre de Usuario</label>
                    <input type="text" id="user" name="user" pattern="<%= UtilesUsuarios.REG_USER%>">
                </div>
                <div class="fieldset">
                    <label for="pass">Contraseña</label>
                    <input type="password" id="pass" name="pass" pattern="<%= UtilesUsuarios.REG_PASS%>">
                </div>
                <div class="buttonset">
                    <input type="submit" value="Enviar">
                    <input type="reset" value="Reiniciar">
                </div>
            </form>
            <div id="boton"><a id="atras" href="?cmd=visita-landing">Atrás</a> </div>

            <div class='light x1'></div>
            <div class='light x2'></div>
            <div class='light x3'></div>
            <div class='light x4'></div>
            <div class='light x5'></div>
            <div class='light x6'></div>
            <div class='light x7'></div>
            <div class='light x8'></div>
            <div class='light x9'></div>
        </div>

    </body>
</html>

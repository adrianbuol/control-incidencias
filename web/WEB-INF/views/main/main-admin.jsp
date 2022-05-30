<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Principal</title>
        <link rel="stylesheet" href="public/css/main/main-admin.css"/>
        <link rel="stylesheet" href="public/css/general/general.css"/>
        <link rel="stylesheet" href="public/css/partials/header.css"/>
    </head>
    <body>
        <%@include file="../partials/header.jspf" %>
        <main>
            <div id="container">
                <h2>Administrador</h2>

                <div id="funciones">
<!--                    <a href="">Dependencia</a>
                    <a href="">Especialidad</a>
                    <a href="">Incidencia</a>
                    <a href="">Notificación</a>
                    <a href="">Perfil</a>-->
                    <a href="?cmd=usuario-listado">Usuario</a>
                </div>

                <div class='light x6'></div>
                <div class='light x5'></div>
                <div class='light x8'></div>
                <div class='light x9'></div>
            </div>
        </main>
    </body>
</html>

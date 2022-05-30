<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Principal</title>
        <link rel="stylesheet" href="public/css/main/main-usuario.css"/>
        <link rel="stylesheet" href="public/css/general/general.css"/>
        <link rel="stylesheet" href="public/css/partials/header.css"/>
    </head>
    <body>
        <%@include file="../partials/header.jspf" %>
        <main>
            <div id="container">
                <img src="public/img/reaper.gif" alt="animacion" />
                <p>Muestra de una página sin permisos</p>
                <a href="?cmd=usuario-logout">Cerrar Sesión</a>
            </div>
        </main>
    </body>
</html>

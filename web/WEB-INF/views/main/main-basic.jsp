<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Principal</title>
        <link rel="stylesheet" href="public/css/main/main-usuario.css"/>
        <link rel="stylesheet" href="public/css/partials/header.css"/>
    </head>
    <body>
        <div id="container">
            <%@include file="../partials/header.jspf" %>
            <main>
                <h2>Pagina Principal</h2>
                <p>Página Principal Usuario Registrado</p>
                <a href="?cmd=usuario-login">Continuar</a>
            </main>
        </div>
    </body>
</html>

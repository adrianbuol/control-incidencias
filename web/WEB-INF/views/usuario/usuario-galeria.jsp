<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.libraries.UtilesUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Datos Inyectados
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Galería</title>
        <link rel="stylesheet" href="public/css/usuario/usuario-galeria.css"/>
        <link rel="stylesheet" href="public/css/general/general.css"/>
        <link rel="stylesheet" href="public/css/partials/header.css"/>
        <link rel="stylesheet" href="public/css/partials/footer.css"/>
        <link rel="icon" type="image/ico" href="public/img/favicon.ico"/>
    </head>
    <body>
        <%@include file="../partials/header.jspf" %>
        <main>
            <div class="galeria">

                <div class="ficha">
                    <% for (Usuario u : usuarios) {%>

                    <a href="?cmd=usuario-consulta&id=<%= u.getId()%>">
                        <img src="<%= u.getAvatar()%>" alt="avatar"/>
                        <div>
                            <p class="id"><%= u.getId()%></p>
                            <p class="id"><%= u.getUser()%></p>
                            <p class="id"><%= u.getPerfilInfo()%></p>
                        </div>
                    </a>
                    <% }%>


                </div>
                <a class="btn btn-atras" href="?cmd=usuario-listado">Atrás</a>
        </main>
        <%@include file="../partials/footer.jspf" %>
    </body>
</html>
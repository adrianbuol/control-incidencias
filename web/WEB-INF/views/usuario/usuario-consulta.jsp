<%@page import="org.japo.java.entities.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario u = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Consulta</title>
        <link rel="stylesheet" href="public/css/usuario/usuario-consulta.css"/>
        <link rel="stylesheet" href="public/css/general/general.css"/>
        <link rel="stylesheet" href="public/css/partials/header.css"/>
        <link rel="stylesheet" href="public/css/partials/footer.css"/>
        <link rel="icon" type="image/ico" href="public/img/favicon.ico"/>
    </head>
    <body>
        <%@include file="../partials/header.jspf" %>
        <main>
            <div class="content">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Perfil</th>
                            <th>Avatar</th>
                        </tr>
                    </thead>
                    <tbody>
                    <td><%= u.getId()%></td>
                    <td><%= u.getUser()%></td>
                    <td><%= u.getPerfilInfo()%></td>
                    <td><img src="<%= u.getAvatar()%>" alt="avatar"/></td>
                    </tbody>
                </table>
            </div>
            <a class="btn btn-atras" href="?cmd=usuario-listado">Atrás</a>
        </main>
    </body>
</html>

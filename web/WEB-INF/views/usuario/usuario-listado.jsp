<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.japo.java.entities.Usuario"%>
<%@page import="org.japo.java.libraries.UtilesUsuarios"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Datos Inyectados
    List<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Funciones</title>
        <link rel="stylesheet" href="public/css/main/main-usuario.css"/>
        <link rel="stylesheet" href="public/css/general/general.css"/>
        <link rel="stylesheet" href="public/css/partials/header.css"/>
    </head>
    <body>
        <%@include file="../partials/header.jspf" %>
        <main>
            <h2>Listado de Usuarios</h2>
            <div>
                <a class="btn btn-insertar" href="?cmd=usuario-insercion">Nuevo</a>
                <a class="btn btn-galeria" href="?cmd=usuario-galeria">Galeria</a>
            </div>



            <table id="lista">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Usuario</th>
                        <th>Perfil Info</th>
                        <th></th>
                    </tr>
                </thead>

                <% for (Usuario u : usuarios) {%>

                <tbody>
                <td class="id"><%= u.getId()%></td>
                <td class="id"><%= u.getUser()%></td>
                <td class="id"><%= u.getPerfilInfo()%></td>
                <td class="funciones">
                    <a href="?cmd=usuario-consulta&id=<%= u.getId()%>">C</a>
                    <a href="">M</a>
                    <a href="?cmd=usuario-borrado&op=proceso&id=<%= u.getId()%>">B</a>
                </td>
                </tbody>
                <% }%>

            </table>
            <a class="btn btn-atras" href="?cmd=usuario-login">Atrás</a>


        </main>
    </body>
</html>
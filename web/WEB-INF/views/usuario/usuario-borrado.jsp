<%@page import="org.japo.java.entities.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
 Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <main>

            <img class="watermark" src="public/img/logo01.png" alt="Logo" />

            <header>
                <h2>Borrado de Usuarios - Confirmación</h2>
                <a class="btn btn-listar" href="?cmd=usuario-listado">Listado</a>
            </header>

            <div class="content">
                <table>
                    <thead>
                        <tr>
                            <th>Dato</th>
                            <th>Valor</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>ID</td>
                            <td><%= usuario.getId()%></td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><%= usuario.getUser()%></td>
                        </tr>
                        <tr>
                            <td>Perfil</td>
                            <td><%= usuario.getPerfilInfo()%></td>
                        </tr>
                    </tbody>
                </table>

                <div class="imagen">
                    <div class="imagen-margen">
                        <img src="<%= usuario.getAvatar()%>" alt="<%= usuario.getUser()%>"/> 
                    </div>
                </div>
            </div>

            <nav class="controles">
                <a class="btn btn-borrar" href="?cmd=usuario-borrado&op=proceso&id=<%= usuario.getId()%>">Borrar</a>
            </nav>

        </main>
    </body>
</html>

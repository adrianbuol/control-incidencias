<%@page import="org.japo.java.libraries.UtilesUsuarios"%>
<%@page import="org.japo.java.libraries.UtilesPerfiles"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Inserción</title>
        <link rel="stylesheet" href="public/css/main/main-usuario.css"/>
        <link rel="stylesheet" href="public/css/general/general.css"/>
        <link rel="stylesheet" href="public/css/partials/header.css"/>
    </head>
    <body>
        <%@include file="../partials/header.jspf" %>
        <main>
            <h2>Inserción de Usuarios</h2>


            <form method="post" 
                  accept-charset="Windows-1252" 
                  enctype="multipart/form-data"
                  action="?cmd=usuario-insercion&op=proceso">
                <input type="hidden" name="perfil" value="<%= UtilesPerfiles.BASIC_CODE%>" />
                <div class="form-content">
                    <div class="field-container">
                        <div class="field-set">
                            <label for="user">Usuario</label>
                            <input id="user" 
                                   type="text" 
                                   name="user" 
                                   pattern="<%= UtilesUsuarios.REG_USER%>" 
                                   required />
                        </div>

                        <div class="field-set">
                            <label for="pass">Contraseña</label>
                            <input id="pass" 
                                   type="text" 
                                   name="pass" 
                                   pattern="<%= UtilesUsuarios.REG_PASS%>" 
                                   required />
                        </div>
                        <div class="avatar-frame">
                            <div class="avatar">
                                <img class="actual" src="<%= UtilesUsuarios.DEF_AVATAR%>" alt="Avatar"/>
                                <img class="backup" src="<%= UtilesUsuarios.DEF_AVATAR%>" alt="Avatar" style="display: none;" />
                            </div>
                        </div>
                        <div class="field-set">
                            <!--<label for="avatar">Avatar</label>-->
                            <form type="text" class="avatar-name">Predeterminado</div>
                        <input id="avatar" 
                               type="file" 
                               name="avatar" 
                               accept="image/png,image/jpeg" 
                               style="display: none" />
                        <button type="button" 
                                class="btn btn-img" 
                                onclick="document.getElementById('avatar').click()">Subir Imagen</button>
                        <div class="controles">
                            <button class="btn btn-submit" type="submit">Enviar</button>
                            <button class="btn btn-reset" type="reset">Reiniciar</button>
                            <a class="btn btn-listar" href="?cmd=usuario-listado">Listado</a>
                        </div>
                        </form>
                    </div>
                </div>

            </form>


        </main>
    </body>
</html>
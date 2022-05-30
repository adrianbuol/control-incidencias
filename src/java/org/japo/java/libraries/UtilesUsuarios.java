/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.libraries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import org.japo.java.dll.usuario.DLLUsuario;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class UtilesUsuarios {

    // Logger
    private static final Logger logger = Logger.getLogger(DLLUsuario.class.getName());

    // DataSource
    DataSource ds;

    public UtilesUsuarios(ServletConfig config) {
        ds = UtilesServlets.obtenerDataSource(config);
    }

    // Valores por defecto
    public static final int DEF_ID = 0;
    public static final String DEF_USER = "Usuario Indefinido";
    public static final String DEF_PASS = "123456";
    public static final String DEF_AVATAR = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQMAAACXljzdAAAABlBMVEUAAAD///+l2Z/dAAAAAnRSTlP/AOW3MEoAAAABYktHRACIBR1IAAAACXBIWXMAAAsTAAALEwEAmpwYAAADnklEQVRYCZXYTY6tNhAF4PJj4CFLIDvxUrIUeCtrZ5RtIGUDDDJAkYNzMafsMlXw0ldqCfrD5tjXP+6mXD//Ev1od1nIQpV62en8TIYsRZyWRNcnKFkhXgmA6C6pSrjJWsXfZKniejkqEM2d7EKmJvyar/x3exHLgmdPcp3gF9cjUlKtfkeLWLbWdn4GsuK98pK49ql9TYMQrkxeE3pgqBLRC4RoY5UN4UjeZH5MihxO5KtE7hEEHaos3Bq0yFVBa1oElqMFQIT5FFw24AdP2VsARJggGw2dRBoh63nVhfOQeJOdBsjSoiGcg8hoCHfJwb32D/3OPTdDnOwwrp7wQozgGbEniOf+QsgVsuE+0vWl4zeEJ3iioEG+SLyac7QpkmiAzLf14IAsl5wBOAK5IuTETPV4pZRYZGhyVdqtLterKSfUIBeKFTLK2Y004SP7JXVFumQqMsl5j5tTNjwm5XwD5bWXUcqs1reDfBFj5SsSSXRBnb7DRxbXLWO4exZ3ymCso/EUMoVOQU4pZ2A6aOwFrZsfZGPp12tIosmQncKLfH4MSU32KqhiepFPCkM+iV8lG5I/sj6IfxOHCdjL4ilestvSlRkvGcwykMF8T6yya1kexL2JN2V9E3oQYkn/o7bwWCbclsGkyyzjc5l36Uf8u7j3MtZ5512mrGc9l/m+TFmvO7KMLXoVQ222oIxcLb8hclW2JShJttz2hXdxGCH9LoMy3jz3orZ+N6vjjcvIHRBzwRZxvaELdG17L65JQnOacJqjkwipDXLZknpygETcyKBYXVDBX3kv0f5U8hNnl594aZVEJULOFC6pq+VKczmDHeSrUD3NHRTqKY9YcDzFoVXKxoFXPFJX/yhO60O3L/AIwAUk4BsI3OFB7D8bj2hcNFl4cERcfOTaAXkQYji2vTHxBNkwtiGjWODbI6HswetNPHZnn5ebuLKjZ/LHDagcW05JSsIlw65kiqcsblMyLq5IVDKUU020hIqQ9TllNcU/yviR7VF2U6ZXSaaEVzlMmT+STcnvshjgikRDhncxO84X2QwZHwV/lzxKMiQUOczOOSUbwn+BKXAQY1T9UlbdUMj2KLuSAEmPYsw5iJ6nVaIODdGzHqJXiiq7jgZJj5J1aJZFh4b04byQTUeDJB0AcnQyQ1QEl6VEFYBlUwFY0uN/i7KQ3Eu0/yulz70s+pwI0WcxiHlGgshuyFpWNFNL5iJajj9+/PbVbv8DMsMDVi1DCioAAAAASUVORK5CYII=";

    // Expresiones regulares
    public static final String REG_USER = "\\w{3,30}";
    public static final String REG_PASS = "\\w{3,30}";

    // Constantes
    public static final int AVATAR_MAX_SIZE = 1024 * 256;

    private UtilesUsuarios() {
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarUser(String user) {
        return user.matches(REG_USER);
    }

    public static final boolean validarPass(String pass) {
        return pass.matches(REG_PASS);
    }

    public static final boolean validarAvatar(String avatar) {
        return UtilesBase64.validarImagenBase64(avatar);
    }

    public static String obtenerComandoVistaPrincipal(HttpServletRequest request) {
        // Salida
        String out;
        // Request > Sesión
        HttpSession sesion = request.getSession(false);

        // Sesion > Usuario
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        // Discriminar Salida
        switch (usuario.getPerfil()) {
            case UtilesPerfiles.BASIC_CODE:
                out = "main/main-basic";
                break;
            case UtilesPerfiles.ADMIN_CODE:
                out = "main/main-admin";
                break;
            default:
                // Esto no debería de ocurrir
                out = "main/main-basic";
        }

        // Retorno salida
        return out;
    }

    public static final int obtenerIdRequest(
            HttpServletRequest request)
            throws IOException {
        // Referencia
        int id;

        // URL > ID Objeto
        try {
            id = Integer.parseInt(request.getParameter("id"));

            if (!validarId(id)) {
                throw new IOException("ID de Usuario Fuera de Rango");
            }
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new IOException("ID de Usuario Incorrecta");
        }

        // Retorno
        return id;
    }

    public static Usuario obtenerUsuarioRequest(
            ServletConfig config,
            HttpServletRequest request) {

        // Capa acceso a datos usuario
        DLLUsuario dllUsuario = new DLLUsuario(config);

        // Request > Nombre de Usuario
        String user = request.getParameter("user");

        // Usuario de Datos + User > Usuario
        Usuario usuario = dllUsuario.consultar(user);

        // Request > Password
        String pass = request.getParameter("pass");

        // Validar Password
        if (!UtilesUsuarios.validarPass(pass)) {
            pass = UtilesUsuarios.DEF_PASS;
        }
        // Validar User
        usuario = usuario != null && usuario.getPass().equals(pass)
                ? usuario : null;

        // Retorno: Usuario
        return usuario;
    }

    public static HttpSession reiniciarSesion(
            ServletConfig config,
            HttpServletRequest request) {
        // Request > Obtener Sesion
        HttpSession sesion = request.getSession(false);

        // Verificar Existencia Sesión
        if (sesion != null) {
            sesion.invalidate();
        }

        // Crear Sesión
        sesion = request.getSession();

        // Config > Lapso
        int lapso = UtilesServlets.obtenerLapsoInactividad(config);

        // Tiempo máximo de sesión inactiva
        sesion.setMaxInactiveInterval(lapso);

        // Retorno: Sesión
        return sesion;
    }

    public static final String obtenerUserRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > User
        String user = request.getParameter("user");

        // Validar User
        if (!validarUser(user)) {
            throw new IOException("Nombre de Usuario Incorrecto");
        }

        // Retorno: Nombre de Usuario
        return user;
    }

    public static final String obtenerPassRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > Pass
        String pass = request.getParameter("pass");

        // Validar Contraseña
        if (!validarPass(pass)) {
            throw new IOException("Contraseña Incorrecta");
        }

        // Retorno: Contraseña
        return pass;
    }

    public static final String obtenerAvatarRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException, ServletException {
        // Imagen Base64
        String avatar;

        // Request > Part
        Part part = request.getPart("avatar");

        // Imagen Enviada
        if (part.getSize() > 0) {
            // Validar Tamaño Avatar
            if (AVATAR_MAX_SIZE <= 0) {
                // No hay tamaño máximo
                avatar = UtilesBase64.obtenerImagenBase64(part);
            } else if (part.getSize() <= AVATAR_MAX_SIZE) {
                // Tamaño Correcto
                avatar = UtilesBase64.obtenerImagenBase64(part);
            } else {
                // Tamaño Excesivo - Avatar Predeterminado
                avatar = DEF_AVATAR;
            }
        } else {
            // Avatar NO modificado - Request + ID Usuario + BD > Usuario
            Usuario usuario = consultarUsuarioIdRequest(config, request);

            // Usuario > Avatar
            avatar = usuario.getAvatar();
        }

        // Retorno: Avatar
        return avatar;
    }

    public static final int obtenerPerfilRequest(
            HttpServletRequest request)
            throws IOException {
        // Request > ID Perfil
        int perfil;
        try {
            if (request.getParameter("perfil") == null) {
                perfil = UtilesPerfiles.BASIC_CODE;
            } else {
                perfil = Integer.parseInt(request.getParameter("perfil"));
            }

            // Validar ID Perfil
            if (!UtilesPerfiles.validarId(perfil)) {
                throw new IOException("Perfil Incorrecto");
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new IOException("Perfil Incorrecto");
        }

        // Retorno: ID Perfil
        return perfil;
    }

    public static final List<Usuario> listarUsuariosPerfil(
            ServletConfig config,
            HttpServletRequest request) {
        // Referencia
        List<Usuario> usuarios;

        // Request > Sesión
        HttpSession sesion = request.getSession(false);

        // Sesión > Usuario               
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        // Capas de Datos
        DLLUsuario dllUsuario = new DLLUsuario(config);

        // Determinar Perfil Usuario
        switch (usuario.getPerfil()) {
            case UtilesPerfiles.DEVEL_CODE:
                // BD > Lista de Usuarios
                usuarios = dllUsuario.listarDev();
                break;
            case UtilesPerfiles.ADMIN_CODE:
                // BD > Lista de Usuarios
                usuarios = dllUsuario.listarDev();
                break;
            case UtilesPerfiles.BASIC_CODE:
            default:
                // Usuario Actual (Únicamente) > Lista de Usuarios
                usuarios = new ArrayList<>();
                usuarios.add(usuario);
        }

        // Retorno: Lista de usuarios visibles por el perfil
        return usuarios;
    }

    public static final Usuario consultarUsuarioIdRequest(
            ServletConfig config,
            HttpServletRequest request)
            throws IOException {
        // Capas de Negocio
        DLLUsuario dllUsuario = new DLLUsuario(config);

        // Request > Id de Usuario
        int id = obtenerIdRequest(request);

        // Retorno: Usuario
        return dllUsuario.consultar(id);
    }

    public static final Usuario obtenerUsuarioUserRequest(
            ServletConfig config,
            HttpServletRequest request) {
        // Capas de Negocio
        DLLUsuario dllUsuario = new DLLUsuario(config);

        // Request > Nombre de Usuario
        String user = request.getParameter("user");

        // Nombre de Usuario + BD > Usuario
        Usuario usuario = dllUsuario.consultar(user);

        // Request > Contraseña de Usuario
        String pass = request.getParameter("pass");

        // Validar Contraseña
        usuario = usuario != null && usuario.getPass().equals(pass)
                ? usuario : null;

        // Retorno: Usuario
        return usuario;
    }

    public static final boolean validarFormatoCredencialRequest(
            HttpServletRequest request) {
        // Request > Credenciales
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        // Validación Formal de la Credencial 
        return true
                && user != null && validarUser(user)
                && pass != null && validarPass(pass);
    }

    public List<Usuario> listarDev() {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.info AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil ";

        // Lista Vacía
        List<Usuario> usuarios = new ArrayList<>();

        try {
            try (
                    Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                // BD > Lista de Entidades
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        Usuario usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);

                        // Entidad > Lista
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return usuarios;
    }
}

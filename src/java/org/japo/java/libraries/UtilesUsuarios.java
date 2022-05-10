/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.libraries;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.japo.java.dll.usuario.DLLUsuario;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class UtilesUsuarios {

    // Valores por defecto
    public static final int DEF_ID = 0;
    public static final String DEF_USER = "Usuario Indefinido";
    public static final String DEF_PASS = "123456";
    public static final String DEF_AVATAR = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQMAAACXljzdAAAABlBMVEUAAAD///+l2Z/dAAAAAnRSTlP/AOW3MEoAAAABYktHRACIBR1IAAAACXBIWXMAAAsTAAALEwEAmpwYAAADnklEQVRYCZXYTY6tNhAF4PJj4CFLIDvxUrIUeCtrZ5RtIGUDDDJAkYNzMafsMlXw0ldqCfrD5tjXP+6mXD//Ev1od1nIQpV62en8TIYsRZyWRNcnKFkhXgmA6C6pSrjJWsXfZKniejkqEM2d7EKmJvyar/x3exHLgmdPcp3gF9cjUlKtfkeLWLbWdn4GsuK98pK49ql9TYMQrkxeE3pgqBLRC4RoY5UN4UjeZH5MihxO5KtE7hEEHaos3Bq0yFVBa1oElqMFQIT5FFw24AdP2VsARJggGw2dRBoh63nVhfOQeJOdBsjSoiGcg8hoCHfJwb32D/3OPTdDnOwwrp7wQozgGbEniOf+QsgVsuE+0vWl4zeEJ3iioEG+SLyac7QpkmiAzLf14IAsl5wBOAK5IuTETPV4pZRYZGhyVdqtLterKSfUIBeKFTLK2Y004SP7JXVFumQqMsl5j5tTNjwm5XwD5bWXUcqs1reDfBFj5SsSSXRBnb7DRxbXLWO4exZ3ymCso/EUMoVOQU4pZ2A6aOwFrZsfZGPp12tIosmQncKLfH4MSU32KqhiepFPCkM+iV8lG5I/sj6IfxOHCdjL4ilestvSlRkvGcwykMF8T6yya1kexL2JN2V9E3oQYkn/o7bwWCbclsGkyyzjc5l36Uf8u7j3MtZ5512mrGc9l/m+TFmvO7KMLXoVQ222oIxcLb8hclW2JShJttz2hXdxGCH9LoMy3jz3orZ+N6vjjcvIHRBzwRZxvaELdG17L65JQnOacJqjkwipDXLZknpygETcyKBYXVDBX3kv0f5U8hNnl594aZVEJULOFC6pq+VKczmDHeSrUD3NHRTqKY9YcDzFoVXKxoFXPFJX/yhO60O3L/AIwAUk4BsI3OFB7D8bj2hcNFl4cERcfOTaAXkQYji2vTHxBNkwtiGjWODbI6HswetNPHZnn5ebuLKjZ/LHDagcW05JSsIlw65kiqcsblMyLq5IVDKUU020hIqQ9TllNcU/yviR7VF2U6ZXSaaEVzlMmT+STcnvshjgikRDhncxO84X2QwZHwV/lzxKMiQUOczOOSUbwn+BKXAQY1T9UlbdUMj2KLuSAEmPYsw5iJ6nVaIODdGzHqJXiiq7jgZJj5J1aJZFh4b04byQTUeDJB0AcnQyQ1QEl6VEFYBlUwFY0uN/i7KQ3Eu0/yulz70s+pwI0WcxiHlGgshuyFpWNFNL5iJajj9+/PbVbv8DMsMDVi1DCioAAAAASUVORK5CYII=";

    // Expresiones regulares
    public static final String REG_USER = "\\w{3,30}";
    public static final String REG_PASS = "\\w{3,30}";

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
        return UtilesBase64.validar(avatar);
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

}

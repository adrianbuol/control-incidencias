package org.japo.java.bll.commands.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.bll.commands.Command;
import org.japo.java.dll.usuario.DLLUsuario;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesUsuarios;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public class CommandUsuarioInsercion extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "usuario/usuario-insercion";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLUsuario dllUsuario = new DLLUsuario(config);

                // Obtener Operación
                String op = request.getParameter("op");

                // Formulario Captura Datos
                if (op == null || op.equals("captura")) {
                    // ---
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    String user = UtilesUsuarios.obtenerUserRequest(request);
                    String pass = UtilesUsuarios.obtenerPassRequest(request);
                    String avatar = UtilesUsuarios.obtenerAvatarRequest(config, request);
                    int perfil = UtilesUsuarios.obtenerPerfilRequest(request);

                    // Parámetros > Usuario
                    Usuario usuario = new Usuario(0, user, pass, avatar, perfil, "");

                    // Entidad > Inserción BD - true | false
                    boolean checkOK = dllUsuario.insertar(usuario);

                    // Validar Operación
                    if (checkOK) {
                        out = "controller?cmd=usuario-listado";
                    } else {
                        out = "message/operacion-cancelada";
                    }
                } else {
                    out = "message/operacion-desconocida";
                }
            } else {
                out = "message/acceso-denegado";
            }
        } else {
            out = "message/sesion-invalida";
        }

        // Redirección
        forward(out);
    }

}

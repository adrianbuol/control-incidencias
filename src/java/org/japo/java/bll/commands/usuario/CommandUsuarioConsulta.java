package org.japo.java.bll.commands.usuario;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.bll.commands.Command;
import org.japo.java.dll.usuario.DLLUsuario;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.UtilesUsuarios;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public class CommandUsuarioConsulta extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "usuario/usuario-consulta";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoAdmin(request.getSession(false))) {
                // Request + ID Usuario + BD > Usuario
                Usuario usuario = UtilesUsuarios.consultarUsuarioIdRequest(config, request);

                // Enlaza Datos > JSP
                request.setAttribute("usuario", usuario);
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

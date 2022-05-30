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
public class CommandUsuarioBorrado extends Command {

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public void process() throws ServletException, IOException {
        // Salida
        String out = "usuario/usuario-borrado";

        // Validar Sesión
        if (validarSesion(request)) {
            // Validador de Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLUsuario dllUsuario = new DLLUsuario(config);

                // request > ID Operación
                String op = request.getParameter("op");

                // ID Entidad + BD > JSP Modificación
                if (op == null || op.equals("captura")) {
                    // Request + ID Usuario + BD > Usuario
                    Usuario usuario = UtilesUsuarios.consultarUsuarioIdRequest(config, request);

                    // Inyecta Datos > JSP
                    request.setAttribute("usuario", usuario);
                } else if (op.equals("proceso")) {
                    // Request > Parámetros
                    int id = UtilesUsuarios.obtenerIdRequest(request);

                    // ID > Registro Borrado - true | false
                    boolean checkOK = dllUsuario.borrar(id);

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

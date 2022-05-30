/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.japo.java.bll.commands.usuario;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.bll.commands.Command;
import org.japo.java.dll.usuario.DLLUsuario;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public class CommandUsuarioGaleria extends Command {

    @Override
    public void process() throws ServletException, IOException {

        // Salida
        String out = "usuario/usuario-galeria";

        // Validar Sesión
        if (validarSesion(request)) {

            // Validar Acceso
            CommandUsuarioValidation validator = new CommandUsuarioValidation(
                    config, request.getSession(false));

            if (validator.validarAccesoAdmin(request.getSession(false))) {
                // Capas de Datos
                DLLUsuario dllUsuario = new DLLUsuario(config);

                // BD > Lista de Registros
                List<Usuario> usuarios = dllUsuario.listar();

                // Inyecta Datos > JSP
                request.setAttribute("usuarios", usuarios);

            } else {
                out = "message/acceso-denegado";
            }

        } else {
            out = "message/acceso-denegado";
        }

        // Redirección
        forward(out);
    }
}

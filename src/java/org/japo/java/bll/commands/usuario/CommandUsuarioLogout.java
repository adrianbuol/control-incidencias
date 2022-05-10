/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.bll.commands.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.japo.java.bll.commands.Command;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public class CommandUsuarioLogout extends Command {

    @Override
    public void process() throws ServletException, IOException {
        // Salida
        String out = "controller?cmd=usuario-login";

        // Validar Usuario YA Identificado
        if (validarSesion(request)) {
            // Cierre artefactos externos 
            // ---

            // Request > Sesion
            HttpSession sesion = request.getSession(false);

            // Cerrar Sesion Acual
            sesion.invalidate();
        }

        // Redirección
        forward(out);
    }
}

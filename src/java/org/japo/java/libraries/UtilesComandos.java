/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.libraries;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.japo.java.bll.commands.ICommand;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class UtilesComandos {

    //Constantes
    private static final String COMMAND_PKG = "org.japo.java.bll.commands";
    private static final String COMMAND_PRE = "Command";

    public static void procesar(
            ServletConfig config,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Request > Nombre de Comando
        String cmdName = request.getParameter("cmd");

        // Nombre del Comando > Objeto Comando
        ICommand cmd = obtenerComando(cmdName);

        // Objeto Comando > Inicializar
        cmd.init(config, request, response);

        // Procesar Comando
        cmd.process();

    }

    private static ICommand obtenerComando(String cmdName)
            throws ServletException {
        // Referencia Comando
        ICommand cmd;

        try {

            // Nombre Comando > Nombre Clase
            String cmdClassName = obtenerNombreComando(cmdName);

            //Nombre Clase > Objeto Class
            Class<?> cmdClass = Class.forName(cmdClassName);

            // Objeto Class > Constructor Clase
            Constructor<?> constructor = cmdClass.getConstructor();

            // Constructor  Clase > Instancia Clase
            cmd = (ICommand) constructor.newInstance();

        } catch (ClassNotFoundException
                | NoSuchMethodException
                | SecurityException
                | InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException ex) {
            //Clase Indefinida o Desconocida
            throw new ServletException(ex.getMessage());
        }

        // Retorno: Comando
        return cmd;

    }

    private static String obtenerNombreComando(String cmd)
            throws ServletException {
        // Subpaquete
        String sub;

        // Parámetro > Subpaquete
        if (cmd == null) {
            throw new ServletException("Comando NO especificado");
        } else if (cmd.equals("validation")) {
            sub = "admin";
        } else if (cmd.contains("-")) {
            sub = cmd.substring(0, cmd.lastIndexOf("-")); // para seleccionar la primera palabra del cmd antes del -
            sub = sub.replace("-", ".");
        } else {
            sub = cmd;
        }

        // Comando Kebab-Case > Comando > Camel-Case
        cmd = UtilesFormato.cambiarKebab2Camel(cmd);

        // Retorno: Nombre Cualificado Clase
        return String.format("%s.%s.%s%s", COMMAND_PKG, sub, COMMAND_PRE, cmd);
    }

}

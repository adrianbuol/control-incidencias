/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.libraries;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class UtilesServlets {

    private UtilesServlets() {
    }

    public static final DataSource obtenerDataSource(String bd)
            throws NamingException {
        // Contexto Inicial Nombrado JNDI
        Context iniCtx = new InitialContext();

        // Situar Contexto Inicial
        Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

        // Contexto Inicial > DataSource
        return (DataSource) envCtx.lookup("jdbc/" + bd);
    }

    public static final DataSource obtenerDataSource(ServletConfig config) {
        // Referencia DataSource
        DataSource ds;

        try {
            // Contexto Inicial Nombrado JNDI
            Context iniCtx = new InitialContext();

            // Situar Contexto Inicial
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Contexto Inicial > DataSource
            ds = (DataSource) envCtx.lookup("jdbc/" + obtenerNombreBD(config));

        } catch (NamingException e) {
            ds = null;
        }

        // Retorno: DataSource
        return ds;
    }

    public static final int obtenerLapsoInactividad(ServletConfig config) {
        // Tiempo Maximo de Inactividad en una Sesión
        int lapso;

        // Parámetro de contexto (web.xml)
        String paramName = "tiempo-maximo-sesion-inactiva";

        try {
            ServletContext context = config.getServletContext();
            String paramValue = context.getInitParameter(paramName);
            lapso = Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            // 1800 seg > Media Hora
            lapso = 1800;
        }
        return lapso;
    }

    public static final String obtenerNombreBD(ServletConfig config) {
        // Parámetro de contexto (web.xml)
        String paramName = "base-datos";

        // Servlet Config > Servlet Context
        ServletContext context = config.getServletContext();

        // Retorno: Nobre Base de Datos
        return context.getInitParameter(paramName);
    }
}

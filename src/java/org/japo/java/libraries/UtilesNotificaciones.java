/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.libraries;

import java.sql.Date;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class UtilesNotificaciones {

    // Códigos de Perfiles
    public static final int BASIC_CODE = 100;
    public static final int ADMIN_CODE = 800;

    // Nombres de Perfiles
    public static final String BASIC_NAME = "Usuario";
    public static final String ADMIN_NAME = "Administrador";

    // Información de Perfiles
    public static final String BASIC_INFO = "Usuario Registrado";
    public static final String ADMIN_INFO = "Usuario con Derechos Administrativos";

    // Valores por defecto
    public static final int DEF_ID = 0;
    public static final String DEF_NOMBRE = "Indefinido";
    public static final String DEF_INFO = "Perfil Indefinido";
    public static final Date DEF_FECHA = new Date(0);
    public static final int DEF_AUTOR = 0;
    public static final int DEF_INCIDENCIA = 0;

    // Expresiones regulares
    public static final String REG_NOMBRE = "[\\w]{3,30}";
    public static final String REG_INFO = "[\\w áéíóúÁÉÍÓÚçÇñÑ]{3,255}";

    private UtilesNotificaciones() {
    }

    public static final boolean validarId(int id) {
        return id >= DEF_ID;
    }

    public static final boolean validarNombre(String nombre) {
        return nombre.matches(REG_NOMBRE);
    }

    public static final boolean validarInfo(String info) {
        return info.matches(REG_INFO);
    }

    public static final boolean validarFecha(Date fecha) {
        return fecha != null;
    }

    public static boolean validarAutor(int autor) {
        return autor >= DEF_AUTOR;
    }

    public static boolean validarIncidencia(int incidencia) {
        return incidencia >= DEF_INCIDENCIA;
    }
}
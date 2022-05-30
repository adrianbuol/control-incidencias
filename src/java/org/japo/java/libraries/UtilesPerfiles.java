/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.libraries;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class UtilesPerfiles {

    // Códigos de Perfiles Básicos
    public static final int VISIT_CODE = 0;
    public static final int BASIC_CODE = 100;
    public static final int ADMIN_CODE = 800;
    public static final int DEVEL_CODE = 900;

    // Nombres de Perfiles Básicos
    public static final String VISIT_NAME = "Visitante";
    public static final String BASIC_NAME = "Usuario";
    public static final String ADMIN_NAME = "Administrador";
    public static final String DEVEL_NAME = "Desarrollador";

    // Descripción de Perfiles Básicos
    public static final String VISIT_INFO = "Usuario NO Identificado";
    public static final String BASIC_INFO = "Usuario Identificado";
    public static final String ADMIN_INFO = "Usuario con Derechos Administrativos";
    public static final String DEVEL_INFO = "Usuario con Derechos de Desarrollo";

    // Valores por Defecto
    public static final int DEF_ID = 0;
    public static final String DEF_NOMBRE = VISIT_NAME;
    public static final String DEF_INFO = VISIT_INFO;

    // Expresiones regulares
    public static final String REG_NOMBRE = "[\\wáéíóúüñÁÉÍÓÚÜÑ]{6,20}";
    public static final String REG_INFO = "[\\wáéíóúüñÁÉÍÓÚÜÑ\\- ]{6,50}";

    private UtilesPerfiles() {
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
}

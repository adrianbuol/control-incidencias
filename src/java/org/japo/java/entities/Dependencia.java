/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

import java.util.Objects;
import org.japo.java.libraries.UtilesDependencias;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class Dependencia {

    //Campos
    private int id;
    private String nombre;
    private String info;

    public Dependencia() {
        this.id = UtilesDependencias.DEF_ID;
        this.nombre = UtilesDependencias.DEF_NOMBRE;
        this.info = UtilesDependencias.DEF_INFO;
    }

    public Dependencia(int id, String nombre, String info) {

        if (UtilesDependencias.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesDependencias.DEF_ID;
        }
        if (UtilesDependencias.validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = UtilesDependencias.DEF_NOMBRE;
        }
        if (UtilesDependencias.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = UtilesDependencias.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesDependencias.validarId(id)) {
            this.id = id;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (UtilesDependencias.validarNombre(nombre)) {
            this.nombre = nombre;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesDependencias.validarInfo(info)) {
            this.info = info;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Dependencia) {
            Dependencia e = (Dependencia) o;
            testOK = id == e.getId()
                    && nombre.equals(e.getNombre())
                    && info.equals(e.getInfo());
        }
        return testOK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.info);
        return hash;
    }

}

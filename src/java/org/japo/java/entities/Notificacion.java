/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

import java.sql.Date;
import java.util.Objects;
import org.japo.java.libraries.UtilesNotificaciones;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class Notificacion {

    // Campos
    private int id;
    private Date fecha;
    private int autor;
    private int incidencia;
    private String info;

    public Notificacion() {
        id = UtilesNotificaciones.DEF_ID;
        info = UtilesNotificaciones.DEF_INFO;
        fecha = UtilesNotificaciones.DEF_FECHA;
        autor = UtilesNotificaciones.DEF_AUTOR;
        incidencia = UtilesNotificaciones.DEF_INCIDENCIA;
        info = UtilesNotificaciones.DEF_INFO;
    }

    public Notificacion(int id, Date fecha, int autor, int incidencia, String info) {
        if (UtilesNotificaciones.validarId(id)) {
            this.id = id;
        } else {
            this.id = id = UtilesNotificaciones.DEF_ID;
        }

        if (UtilesNotificaciones.validarFecha(fecha)) {
            this.fecha = fecha;
        } else {
            this.fecha = fecha = UtilesNotificaciones.DEF_FECHA;
        }

        if (UtilesNotificaciones.validarAutor(autor)) {
            this.autor = autor;
        } else {
            this.autor = autor = UtilesNotificaciones.DEF_AUTOR;
        }

        if (UtilesNotificaciones.validarIncidencia(incidencia)) {
            this.incidencia = incidencia;
        } else {
            this.incidencia = incidencia = UtilesNotificaciones.DEF_INCIDENCIA;
        }

        if (UtilesNotificaciones.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = info = UtilesNotificaciones.DEF_INFO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesNotificaciones.validarId(id)) {
            this.id = id;
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        if (UtilesNotificaciones.validarFecha(fecha)) {
            this.fecha = fecha;
        }
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        if (UtilesNotificaciones.validarAutor(autor)) {
            this.autor = autor;
        }
    }

    public int getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(int incidencia) {
        if (UtilesNotificaciones.validarIncidencia(incidencia)) {
            this.incidencia = incidencia;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesNotificaciones.validarInfo(info)) {
            this.info = info;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.fecha);
        hash = 47 * hash + this.autor;
        hash = 47 * hash + this.incidencia;
        hash = 47 * hash + Objects.hashCode(this.info);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Notificacion) {
            Notificacion e = (Notificacion) o;
            testOK = id == e.getId()
                    && fecha.equals(e.getFecha())
                    && autor == e.getAutor()
                    && incidencia == e.getIncidencia()
                    && info.equals(e.getInfo());
        }
        return testOK;
    }
}

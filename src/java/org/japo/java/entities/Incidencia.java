/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

import java.sql.Date;
import java.util.Objects;
import org.japo.java.libraries.UtilesIncidencias;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public class Incidencia {

    // Campos
    private int id;
    private String nombre;
    private String info;
    private int estado;
    private Date creacion;
    private int autor;
    private int dependencia;
    private int especialidad;

    public Incidencia() {
        id = UtilesIncidencias.DEF_ID;
        nombre = UtilesIncidencias.DEF_NOMBRE;
        info = UtilesIncidencias.DEF_INFO;
        estado = UtilesIncidencias.DEF_ESTADO;
        creacion = UtilesIncidencias.DEF_FECHA;
        autor = UtilesIncidencias.DEF_AUTOR;
        dependencia = UtilesIncidencias.DEF_DEPENDENCIA;
        especialidad = UtilesIncidencias.DEF_ESPECIALIDAD;
    }

    public Incidencia(int id, String nombre, String info, int estado,
            Date creacion, int autor, int dependencia, int especialidad) {
        if (UtilesIncidencias.validarId(id)) {
            this.id = id;
        } else {
            this.id = id = UtilesIncidencias.DEF_ID;
        }

        if (UtilesIncidencias.validarNombre(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = nombre = UtilesIncidencias.DEF_NOMBRE;
        }

        if (UtilesIncidencias.validarInfo(info)) {
            this.info = info;
        } else {
            this.info = info = UtilesIncidencias.DEF_INFO;
        }

        if (UtilesIncidencias.validarEstado(estado)) {
            this.estado = estado;
        } else {
            this.estado = estado = UtilesIncidencias.DEF_ESTADO;
        }

        if (UtilesIncidencias.validarCreacion(creacion)) {
            this.creacion = creacion;
        } else {
            this.creacion = creacion = UtilesIncidencias.DEF_FECHA;
        }

        if (UtilesIncidencias.validarAutor(autor)) {
            this.autor = autor;
        } else {
            this.autor = autor = UtilesIncidencias.DEF_AUTOR;
        }

        if (UtilesIncidencias.validarDependencia(dependencia)) {
            this.dependencia = dependencia;
        } else {
            this.dependencia = dependencia = UtilesIncidencias.DEF_DEPENDENCIA;
        }

        if (UtilesIncidencias.validarEspecialidad(especialidad)) {
            this.especialidad = especialidad;
        } else {
            this.especialidad = especialidad = UtilesIncidencias.DEF_ESPECIALIDAD;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (UtilesIncidencias.validarId(id)) {
            this.id = id;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (UtilesIncidencias.validarNombre(nombre)) {
            this.nombre = nombre;
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        if (UtilesIncidencias.validarInfo(info)) {
            this.info = info;
        }
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        if (UtilesIncidencias.validarEstado(estado)) {
            this.estado = estado;
        }
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        if (UtilesIncidencias.validarCreacion(creacion)) {
            this.creacion = creacion;
        }
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        if (UtilesIncidencias.validarAutor(autor)) {
            this.autor = autor;
        }
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        if (UtilesIncidencias.validarDependencia(dependencia)) {
            this.dependencia = dependencia;
        }
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        if (UtilesIncidencias.validarEspecialidad(especialidad)) {
            this.especialidad = especialidad;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Incidencia) {
            Incidencia e = (Incidencia) o;
            testOK = id == e.getId()
                    && nombre.equals(e.getNombre())
                    && info.equals(e.getInfo())
                    && estado == e.getEstado()
                    && creacion.equals(e.getCreacion())
                    && autor == e.getAutor()
                    && dependencia == e.getDependencia()
                    && especialidad == e.getEspecialidad();
        }
        return testOK;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + Objects.hashCode(this.info);
        hash = 61 * hash + this.estado;
        hash = 61 * hash + Objects.hashCode(this.creacion);
        hash = 61 * hash + this.autor;
        hash = 61 * hash + this.dependencia;
        hash = 61 * hash + this.especialidad;
        return hash;
    }

}

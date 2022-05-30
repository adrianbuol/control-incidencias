package org.japo.java.entities;

import java.io.Serializable;
import java.util.Objects;
import org.japo.java.libraries.UtilesPerfiles;
import org.japo.java.libraries.UtilesUsuarios;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class Usuarios implements Serializable {

    //Campos
    private int id;
    private String user;
    private String pass;
    private String avatar;
    private int perfil;

    public Usuarios() {
        id = UtilesUsuarios.DEF_ID;
        user = UtilesUsuarios.DEF_USER;
        pass = UtilesUsuarios.DEF_PASS;
        avatar = UtilesUsuarios.DEF_AVATAR;
        perfil = UtilesPerfiles.DEF_ID;
    }

    public Usuarios(int id, String user, String pass, String avatar, int perfil) {
        if (UtilesUsuarios.validarId(id)) {
            this.id = id;
        } else {
            this.id = UtilesUsuarios.DEF_ID;
        }

        if (UtilesUsuarios.validarUser(user)) {
            this.user = user;
        } else {
            this.user = UtilesUsuarios.DEF_USER;
        }

        if (UtilesUsuarios.validarPass(pass)) {
            this.pass = pass;
        } else {
            this.pass = UtilesUsuarios.DEF_PASS;
        }

        if (UtilesUsuarios.validarAvatar(avatar)) {
            this.avatar = avatar;
        } else {
            this.avatar = UtilesUsuarios.DEF_AVATAR;
        }

        if (UtilesPerfiles.validarId(perfil)) {
            this.perfil = perfil;
        } else {
            this.perfil = UtilesPerfiles.DEF_ID;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (validarId()) {
            this.id = id;
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        if (validarUser()) {
            this.user = user;
        }
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (validarPass()) {
            this.pass = pass;
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        if (validarAvatar()) {
            this.avatar = avatar;
        }
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        if (validarPerfil()) {
            this.perfil = perfil;
        }
    }

    private boolean validarId() {
        return UtilesUsuarios.validarId(id);
    }

    private boolean validarUser() {
        return UtilesUsuarios.validarUser(user);
    }

    private boolean validarPass() {
        return UtilesUsuarios.validarPass(pass);
    }

    private boolean validarAvatar() {
        return UtilesUsuarios.validarAvatar(avatar);
    }

    private boolean validarPerfil() {
        return UtilesPerfiles.validarId(perfil);
    }

    @Override
    public boolean equals(Object o) {
        boolean testOK = false;
        if (o instanceof Usuario) {
            Usuario u = (Usuario) o;
            testOK
                    = id == u.getId()
                    && user.equals(u.getUser())
                    && pass.equals(u.getPass())
                    && avatar.equals(u.getAvatar())
                    && perfil == u.getPerfil();

        }
        return testOK;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.pass);
        hash = 59 * hash + Objects.hashCode(this.avatar);
        hash = 59 * hash + this.perfil;
        return hash;
    }

}

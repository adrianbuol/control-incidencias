package org.japo.java.dll.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.sql.DataSource;
import org.japo.java.entities.Usuario;
import org.japo.java.entities.Usuarios;
import org.japo.java.libraries.UtilesServlets;

/**
 *
 * @author Adrián Bueno Olmedo <adrian.bueno.alum@iescamp.es>
 */
public final class DLLUsuario {

    // Logger
    private static final Logger logger = Logger.getLogger(DLLUsuario.class.getName());

    // Acceso a la base de datos (Pool de conexiones)
    private DataSource ds;

    public DLLUsuario(ServletConfig config) {
        ds = UtilesServlets.obtenerDataSource(config);
    }

    public Usuario consultar(int id) {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.info AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "WHERE usuarios.id=?";

        // Entidad
        Usuario usuario = null;

        try {
            try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // BD > Lista de Entidades
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos 
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Entidad
        return usuario;
    }

    public final Usuario consultar(String user) {
        //Referencia
        Usuario usuario = null;

        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.info AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil "
                + "WHERE "
                + "usuarios.user=?";

        //Búsqueda
        try {
            try (
                    Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);) {
                // Parametrizar Sentencia
                ps.setString(1, user);

                // BD > Lista de Entidades
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Fila Actual > Campos
                        int id = rs.getInt("id");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);
                    }
                }
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Retorno
        return usuario;

    }

    public List<Usuario> listar() {

        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.info AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil";

        // Coleccion 
        List<Usuario> usuarios = new ArrayList<>();

        // Busqueda
        try {
            try (
                    Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                // BD > Lista de Entidades
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // File Actual > Campos
                        int id = rs.getInt("id");
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        Usuario usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);

                        usuarios.add(usuario);
                    }
                }

            }

        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Retorno
        return usuarios;
    }

    public List<Usuario> listarDev() {
        // SQL
        String sql = ""
                + "SELECT "
                + "usuarios.id AS id, "
                + "usuarios.user AS user, "
                + "usuarios.pass AS pass, "
                + "usuarios.avatar AS avatar, "
                + "usuarios.perfil AS perfil, "
                + "perfiles.info AS perfil_info "
                + "FROM "
                + "usuarios "
                + "INNER JOIN "
                + "perfiles ON perfiles.id = usuarios.perfil ";

        // Lista Vacía
        List<Usuario> usuarios = new ArrayList<>();

        try {
            try (
                    Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                // BD > Lista de Entidades
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Fila Actual > Campos 
                        int id = rs.getInt("id");
                        String user = rs.getString("user");
                        String pass = rs.getString("pass");
                        String avatar = rs.getString("avatar");
                        int perfil = rs.getInt("perfil");
                        String perfilInfo = rs.getString("perfil_info");

                        // Campos > Entidad
                        Usuario usuario = new Usuario(id, user, pass, avatar, perfil, perfilInfo);

                        // Entidad > Lista
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno Lista
        return usuarios;
    }

    public boolean borrar(int id) {
        // SQL
        String sql = ""
                + "DELETE FROM usuarios "
                + "WHERE id=?";

        // Número de registros afectados
        int numReg = 0;

        try {
            try (
                    Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public boolean insertar(Usuario usuario) {
        // SQL
        String sql = ""
                + "INSERT INTO "
                + "usuarios "
                + "("
                + "user, pass, avatar, perfil"
                + ") "
                + "VALUES (?, ?, ?, ?)";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            try (
                    Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setString(1, usuario.getUser());
                ps.setString(2, usuario.getPass());
                ps.setString(3, usuario.getAvatar());
                ps.setInt(4, usuario.getPerfil());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }

    public boolean insertar(Usuarios usuario) {
        // SQL
        String sql = ""
                + "INSERT INTO "
                + "usuarios "
                + "("
                + "user, pass, avatar, perfil"
                + ") "
                + "VALUES (?, ?, ?, ?)";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            try (
                    Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                // Parametrizar Sentencia
                ps.setString(1, usuario.getUser());
                ps.setString(2, usuario.getPass());
                ps.setString(3, usuario.getAvatar());
                ps.setInt(4, usuario.getPerfil());

                // Ejecutar Sentencia
                numReg = ps.executeUpdate();
            }
        } catch (SQLException | NullPointerException ex) {
            logger.info(ex.getMessage());
        }

        // Retorno: true | false
        return numReg == 1;
    }
}

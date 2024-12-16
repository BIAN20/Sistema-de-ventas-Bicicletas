package DAO;

import Model.Trabajador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();

    public List<Trabajador> listarTrabajadores() {
        ArrayList<Trabajador> listar = new ArrayList<>();
        String sql = "{CALL ListarTrabajadores()}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Trabajador tra = new Trabajador();
                tra.setIdTrabajador(rs.getInt(1));
                tra.setNombre(rs.getString(2));
                tra.setApellidos(rs.getString(3));
                tra.setNroIdentificacion(rs.getString(4));
                tra.setEmail(rs.getString(5));
                tra.setDireccion(rs.getString(6));
                tra.setTelefono(rs.getString(7));
                tra.setCargo(rs.getString(8));
                tra.setSueldo(rs.getDouble(9));
                tra.setEstado(rs.getString(10));
                listar.add(tra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listar;
    }

    public void registrarTrabajador(String nombre, String apellidos, String nroIdentificacion, String email, String direccion, String telefono, String cargo, double sueldo) throws SQLException {
        String sql = "{CALL RegistrarTrabajador(?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, nroIdentificacion);
            ps.setString(4, email);
            ps.setString(5, direccion);
            ps.setString(6, telefono);
            ps.setString(7, cargo);
            ps.setDouble(8, sueldo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al registrar el trabajador", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void actualizarTrabajador(int idTrabajador, String nombre, String apellidos, String dni, String direccion, String telefono, String email, String cargo, double sueldo, String estado) throws SQLException {
        String sql = "{CALL ActualizarTrabajador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, idTrabajador);
            cs.setString(2, nombre);
            cs.setString(3, apellidos);
            cs.setString(4, dni);
            cs.setString(5, direccion);
            cs.setString(6, telefono);
            cs.setString(7, email);
            cs.setString(8, cargo);
            cs.setDouble(9, sueldo);
            cs.setString(10, estado);
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al actualizar el trabajador", e);
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Trabajador obtenerTrabajadorPorId(int idTrabajador) throws SQLException {
        Trabajador trabajador = null;
        String sql = "{CALL ObtenerTrabajadorPorId(?)}";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, idTrabajador);
            rs = cs.executeQuery();
            if (rs.next()) {
                trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("ID_Trabajador"));
                trabajador.setNombre(rs.getString("Nombre"));
                trabajador.setApellidos(rs.getString("Apellidos"));
                trabajador.setNroIdentificacion(rs.getString("DNI"));
                trabajador.setDireccion(rs.getString("Direccion"));
                trabajador.setTelefono(rs.getString("Telefono"));
                trabajador.setEmail(rs.getString("Email"));
                trabajador.setCargo(rs.getString("Cargo"));
                trabajador.setSueldo(rs.getDouble("Sueldo"));
                trabajador.setEstado(rs.getString("Estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener el trabajador", e);
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return trabajador;
    }

    public List<Trabajador> buscarTrabajador(String criterio) {
        List<Trabajador> listaTrabajador = new ArrayList<>();
        String sql = "{CALL BuscarTrabajador(?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, criterio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Trabajador tra = new Trabajador();
                tra.setIdTrabajador(rs.getInt("idTrabajador"));
                tra.setNombre(rs.getString("nombre"));
                tra.setApellidos(rs.getString("apellidos"));
                tra.setNroIdentificacion(rs.getString("nroIdentificacion"));
                tra.setDireccion(rs.getString("direccion"));
                tra.setTelefono(rs.getString("telefono"));
                tra.setEmail(rs.getString("email"));
                tra.setCargo(rs.getString("cargo"));
                tra.setSueldo(rs.getDouble("sueldo"));
                listaTrabajador.add(tra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaTrabajador;
    }

    public void eliminarTrabajador(int idTrabajador) throws SQLException {
        String sql = "{CALL EliminarTrabajador(?)}";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, idTrabajador);
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al eliminar el trabajador", e);
        } finally {
            // Cerramos los recursos
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}

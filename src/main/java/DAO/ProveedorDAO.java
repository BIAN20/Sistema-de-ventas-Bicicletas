package DAO;

import Model.Proveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();

    public List<Proveedor> listarProveedores() {
        ArrayList<Proveedor> listar = new ArrayList<>();
        String sql = "{CALL ListarProveedores()}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt(1));
                prov.setNombre(rs.getString(2));
                prov.setApellidos(rs.getString(3));
                prov.setNroIdentificacion(rs.getString(4));
                prov.setDireccion(rs.getString(5));
                prov.setTelefono(rs.getString(6));
                prov.setEmail(rs.getString(7));
                prov.setNombreEmpresa(rs.getString(8));
                listar.add(prov);
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
 

    public void registrarProveedor(String nombre, String apellidos, String nroIdentificacion, String direccion, String telefono, String email, String nombreEmpresa) throws SQLException {
        String sql = "{CALL RegistrarProveedor(?, ?, ?, ?, ?, ?, ?}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, nroIdentificacion); 
            ps.setString(4, direccion);
            ps.setString(5, telefono);
            ps.setString(6, email);
            ps.setString(7, nombreEmpresa);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al registrar el proveedor", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Proveedor obtenerProveedorPorId(int idProveedor) throws SQLException {
        Proveedor proveedor = null;
        String sql = "{CALL ObtenerProveedorPorId(?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idProveedor);
            rs = ps.executeQuery();
            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("ID_Proveedor"));
                proveedor.setNombre(rs.getString("Nombre"));
                proveedor.setApellidos(rs.getString("Apellidos"));
                proveedor.setNroIdentificacion(rs.getString("DNI"));
                proveedor.setDireccion(rs.getString("Direccion"));
                proveedor.setTelefono(rs.getString("Telefono"));
                proveedor.setEmail(rs.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener el proveedor", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return proveedor;
    }

    public void actualizarProveedor(int idProveedor, String nombre, String apellidos, String dni, String direccion, String telefono, String email) throws SQLException {
        String sql = "{CALL ActualizarProveedor(?, ?, ?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idProveedor);
            cs.setString(2, nombre);
            cs.setString(3, apellidos);
            cs.setString(4, dni);
            cs.setString(5, direccion);
            cs.setString(6, telefono);
            cs.setString(7, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al actualizar el Proveedor", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<Proveedor> buscarProveedor(String criterio) {
        List<Proveedor> listaProveedores = new ArrayList<>();
        String sql = "{CALL BuscarProveedor(?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, criterio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setApellidos(rs.getString("apellidos"));
                proveedor.setNroIdentificacion(rs.getString("nroIdentificacion"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEmail(rs.getString("email"));
                listaProveedores.add(proveedor);
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
        return listaProveedores;
    }
    

        public void eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "{CALL EliminarProveedor(?)}";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, idProveedor);
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
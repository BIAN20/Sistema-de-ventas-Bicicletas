package DAO;

import Model.Cliente;
import Model.TipoCliente;
import Model.estadoPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteDAO {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();

    public List<Cliente> listarClientes() {
        ArrayList<Cliente> listar = new ArrayList<>();
        String sql = "{CALL ListarClientes()}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellidos(rs.getString(3));
                cli.setNroIdentificacion(rs.getString(4));
                cli.setEmail(rs.getString(5));
                cli.setDireccion(rs.getString(6));
                cli.setTelefono(rs.getString(7));

                // Configurar TipoCliente
                TipoCliente tipoCliente = new TipoCliente();
                tipoCliente.setNombreTipo(rs.getString(8));
                cli.setTipoCliente(tipoCliente);

                // Configurar Estado (mapeo a enum)
                String estadoStr = rs.getString(9); // estado en String desde la BD
                estadoPersona estado = estadoPersona.valueOf(estadoStr.toUpperCase()); // Mapeo a enum
                cli.setEstado(estado);

                listar.add(cli);
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

    public void registrarCliente(String nombre, String apellidos, String nroIdentificacion, String email, String direccion, String telefono, String tipoCliente, String ruc) throws SQLException {
        String sql = "{CALL RegistrarCliente(?, ?, ?, ?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, nroIdentificacion);
            ps.setString(4, email);
            ps.setString(5, direccion);
            ps.setString(6, telefono);
            ps.setString(7, tipoCliente);
            ps.setString(8, ruc);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al registrar el cliente", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Cliente obtenerClientePorId(int idCliente) throws SQLException {
        Cliente cliente = null;
        String sql = "{CALL ObtenerClientePorId(?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellidos(rs.getString("Apellidos"));
                cliente.setNroIdentificacion(rs.getString("DNI"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setTelefono(rs.getString("Telefono"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener el cliente", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return cliente;
    }

    public void actualizarCliente(int idCliente, String nombre, String apellidos, String nroIdentificacion, String email, String direccion, String telefono) throws SQLException {
        String sql = "{CALL ActualizarCliente(?, ?, ?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idCliente);
            ps.setString(2, nombre);
            ps.setString(3, apellidos);
            ps.setString(4, nroIdentificacion);
            ps.setString(5, email);
            ps.setString(6, direccion);
            ps.setString(7, telefono);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al actualizar el cliente", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<Cliente> buscarCliente(String criterio) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "{CALL BuscarCliente(?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, criterio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setNroIdentificacion(rs.getString("nroIdentificacion"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                TipoCliente tipoCliente = new TipoCliente();
                tipoCliente.setNombreTipo(rs.getString("nombreTipo"));
                cliente.setTipoCliente(tipoCliente);
                listaClientes.add(cliente);
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
        return listaClientes;
    }

    public boolean eliminarCliente(int idCliente) throws SQLException {
        Connection con = null;
        CallableStatement stmt = null;
        String sql = "{CALL EliminarCliente(?)}";  // Nombre del procedimiento almacenado

        try {
            con = cn.getConnection();
            stmt = con.prepareCall(sql);
            stmt.setInt(1, idCliente);

            int result = stmt.executeUpdate();
            System.out.println("Resultado de la eliminación: " + result);

            return result > 0;
        } catch (SQLException e) {
            System.err.println("Error al ejecutar el procedimiento almacenado: " + e.getMessage());
            e.printStackTrace();  // Imprimir la traza del error
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

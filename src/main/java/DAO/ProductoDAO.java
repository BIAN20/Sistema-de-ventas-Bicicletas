package DAO;

import Model.CategoriaProducto;
import Model.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();

    public List<Producto> listarProductos() {
        ArrayList<Producto> listar = new ArrayList<>();
        String sql = "{CALL ListarProductos()}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setIdProducto(rs.getInt(1));
                pro.setNombreCat(rs.getString(2));
                pro.setNombreProducto(rs.getString(3));
                pro.setPrecio(rs.getDouble(4));
                pro.setStock(rs.getInt(5));
                pro.setDescripcion(rs.getString(6));

                listar.add(pro);
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

    public void registrarProducto(String nombre, double precio, int stock, String descripcion, String nombreCat) throws SQLException {
        String sql = "{CALL RegistrarProducto(?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setString(4, descripcion);
            ps.setString(5, nombreCat);
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

    public List<CategoriaProducto> obtenerCategorias() throws SQLException {
        List<CategoriaProducto> categorias = new ArrayList<>();
        String sql = "{CALL ObtenerCategorias()}"; // Asegúrate de que el procedimiento no necesite parámetros.
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                CategoriaProducto cat = new CategoriaProducto();
                cat.setNombreCat(rs.getString("nombreCat"));
                categorias.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener las categorías", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return categorias;
    }

}

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

    public List<Producto> listarProductosConPaginacion(int limite, int offset) {
        ArrayList<Producto> listar = new ArrayList<>();
        String sql = "{CALL ListarProductosPaginados(?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, limite);
            ps.setInt(2, offset);
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

    public int contarProductos() {
        String sql = "{CALL ContarProductos()}";
        int total = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return total;
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
            throw new SQLException("Error al registrar el producto", e);
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
        String sql = "{CALL ObtenerCategorias()}";
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

    public void actualizarProducto(int idProducto, String nombreProducto, double precio, int stock, String descripcion, String nombreCat) throws SQLException {
        String sql = "{CALL ActualizarProducto(?, ?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, idProducto);
            cs.setString(2, nombreProducto);
            cs.setDouble(3, precio);
            cs.setInt(4, stock);
            cs.setString(5, descripcion);
            cs.setString(6, nombreCat);
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al actualizar el producto", e);
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Producto obtenerProductoPorId(int idProducto) throws SQLException {
        Producto producto = null;
        String sql = "{CALL ObtenerProductoPorId(?)}";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, idProducto);
            rs = cs.executeQuery();
            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("ID_Producto"));
                producto.setNombreProducto(rs.getString("NombreProducto"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setStock(rs.getInt("Stock"));
                producto.setNombreCat(rs.getString("NombreCategoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener el producto", e);
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
        return producto;
    }

    public void eliminarProducto(int idProducto) throws SQLException {
        String sql = "{CALL EliminarProducto(?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al eliminar el producto", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<Producto> BuscarProducto(String criterio) {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "{CALL BuscarProducto(?)}";

        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            // Establecer el parámetro del procedimiento almacenado
            ps.setString(1, criterio);

            try (ResultSet rs = ps.executeQuery()) {
                // Iterar sobre los resultados y añadirlos a la lista
                while (rs.next()) {
                    Producto pro = new Producto();
                    pro.setIdProducto(rs.getInt("idProducto"));
                    pro.setNombreCat(rs.getString("categoria")); // Nombre del alias en el procedimiento
                    pro.setNombreProducto(rs.getString("nombreProducto"));
                    pro.setPrecio(rs.getDouble("precio"));
                    pro.setStock(rs.getInt("stock"));
                    pro.setDescripcion(rs.getString("descripcion"));
                    listaProductos.add(pro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Puedes cambiar esto por un log para ambientes de producción
        }

        return listaProductos;
    }

}

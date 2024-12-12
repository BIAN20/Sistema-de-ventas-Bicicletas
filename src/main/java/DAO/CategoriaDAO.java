package DAO;

import Model.CategoriaProducto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection con;
    Conexion cn = new Conexion();

    public List<CategoriaProducto> listarCategorias() {
        List<CategoriaProducto> listaCategorias = new ArrayList<>();
        String sql = "{CALL ListarCategoria()}";

        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                CategoriaProducto cat = new CategoriaProducto();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setNombreCat(rs.getString("nombreCat"));
                cat.setDescripcion(rs.getString("descripcion"));
                listaCategorias.add(cat);
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

        return listaCategorias;
    }

    public boolean registrarCategoria(CategoriaProducto cat) {
        String sql = "{CALL RegistrarCategoria(?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setString(1, cat.getNombreCat());
            ps.setString(2, cat.getDescripcion());
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
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
    }

    public void actualizarCategoria(int idCategoria, String nombrecat, String descripcion) {
        String sql = "{CALL ActualizarCategoria(?, ?, ?)}";
        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idCategoria);
            ps.setString(2, nombrecat);
            ps.setString(3, descripcion);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
    }

    public CategoriaProducto obtenerCategoriaPorId(int idCategoria) throws SQLException {
        CategoriaProducto categoria = null;
        String sql = "{CALL ObtenerCategoriaPorId(?)}";
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, idCategoria);
            rs = cs.executeQuery();
            if (rs.next()) {
                categoria = new CategoriaProducto();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombreCat(rs.getString("nombreCat"));
                categoria.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener la categoria", e);
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return categoria;
    }

    public boolean eliminarCategoria(int idCategoria) {
        String sql = "{CALL EliminarCategoria(?)}";

        try {
            con = cn.getConnection();
            ps = con.prepareCall(sql);
            ps.setInt(1, idCategoria);

            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
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
    }

    public List<CategoriaProducto> buscarCategoria(String criterio) {
        List<CategoriaProducto> listaCategorias = new ArrayList<>();
        String sql = "{CALL BuscarCategoria(?)}";
        if (criterio == null || criterio.trim().isEmpty()) {
            criterio = "";
        }
        try (Connection con = cn.getConnection(); CallableStatement ps = con.prepareCall(sql)) {
            ps.setString(1, criterio.trim()); 

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoriaProducto categoria = new CategoriaProducto();
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setNombreCat(rs.getString("nombreCat"));
                    categoria.setDescripcion(rs.getString("descripcion"));
                    listaCategorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar categorías: " + e.getMessage());
        }
        return listaCategorias;
    }

}

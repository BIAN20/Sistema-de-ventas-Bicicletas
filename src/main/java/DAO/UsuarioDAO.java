
package DAO;

import Model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


public class UsuarioDAO {
    
    public Usuario  autenticarUsuario( String usuario, String contraseña ){
        Usuario user = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection con = null;        
        Conexion cn = new Conexion();
        
        try {
            con = cn.getConnection();
            String sql = "{CALL validarUsuario(?, ?)}"; 
            cs = con.prepareCall(sql);
            cs.setString(1, usuario);
            cs.setString(2, contraseña);
            rs = cs.executeQuery();
            
            if (rs.next()) {
                user = new Usuario();
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setIdRol(rs.getInt("idRol")); // Corrección: ahora asigna correctamente idRol
                user.setIdTrabajador(rs.getInt("idTrabajador"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mejor: registrar el error en un log
        } finally {
            // Cierra los recursos para evitar fugas
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return user;
    }
    
    
}


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
            con = (Connection) cn.getConnection();
            String sql ="{CALL validarUsuario(?,?)}"; 
            cs = con.prepareCall(sql);
            cs.setString(1, usuario );
            cs.setString(2, contraseña );
            rs = cs.executeQuery();
            while( rs.next()){
                user = new Usuario();
                user.setIdUsuario(rs.getInt("idUsuario") );
                user.setUser(rs.getString("user") );
                user.setPassword(rs.getString("password"));
                user.setIdTrabajador(rs.getInt("idrol"));
                user.setIdTrabajador(rs.getInt("idtrabajador"));
                
            }
        } catch (Exception e) {
            
        }finally{
            //....
        }
        return user;
    }
    
    
}

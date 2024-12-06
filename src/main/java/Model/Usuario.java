
package Model;


public class Usuario {
    private int idUsuario;
    private String user;
    private String password;
    private int idTrabajador;
    private int idRol;

    public Usuario(int idUsuario, String user, String password, int idTrabajador, int idRol) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
        this.idTrabajador = idTrabajador;
        this.idRol = idRol;
    }

    public Usuario() {
    }

    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    
    
}

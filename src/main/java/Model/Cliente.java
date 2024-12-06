
package Model;

public class Cliente extends Persona {
    private int idCliente;
    private TipoCliente tipoCliente;

    public Cliente() {
    }

    public Cliente(int idPersona, String nombre, String apellidos, String nroIdentificacion, String direccion, String telefono, String email) {
        super(idPersona, nombre, apellidos, nroIdentificacion, direccion, telefono, email);
    }

    public Cliente(int idCliente, TipoCliente tipoCliente) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
    }

    public Cliente(int idCliente, TipoCliente tipoCliente, int idPersona, String nombre, String apellidos, String nroIdentificacion, String direccion, String telefono, String email) {
        super(idPersona, nombre, apellidos, nroIdentificacion, direccion, telefono, email);
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    
    
}

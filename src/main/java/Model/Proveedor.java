package Model;

public class Proveedor extends Persona {
    private int idProveedor;
    private String nombreEmpresa;

    public Proveedor() {
    }
    
    public Proveedor(int idProveedor) {
        this.idProveedor = idProveedor;
        this.nombreEmpresa = nombreEmpresa;
    }

    public Proveedor(int idProveedor, int idPersona, String nombre, String apellidos, String nroIdentificacion, String direccion, String telefono, String email) {
        super(idPersona, nombre, apellidos, nroIdentificacion, direccion, telefono, email);
        this.idProveedor = idProveedor;
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;        
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
}


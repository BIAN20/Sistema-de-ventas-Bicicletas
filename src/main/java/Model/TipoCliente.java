
package Model;

public class TipoCliente {
    private int idTipoCliente;
    private String nombreTipo;
    private String ruc;

    public TipoCliente(int idTipoCliente, String nombreTipo, String ruc) {
        this.idTipoCliente = idTipoCliente;
        this.nombreTipo = nombreTipo;
        this.ruc = ruc;
    }

    public TipoCliente() {
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    
    
}


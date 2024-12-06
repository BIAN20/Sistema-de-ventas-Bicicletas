
package Model;


public class CategoriaProducto {
    private int idCategoria;
    private String nombreCat;
    private String descripcion;

    public CategoriaProducto() {
    }

    public CategoriaProducto(int idCategoria, String nombreCat, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombreCat = nombreCat;
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}

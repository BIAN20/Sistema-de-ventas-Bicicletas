
package Model;

public class Producto extends CategoriaProducto {
    private int idProducto;
    private String nombreProducto;
    private String Descripcion;
    private double precio;
    private int stock;

    public Producto() {
    }

    public Producto(int idProducto, String nombreProducto, String Descripcion, double precio, int stock) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.Descripcion = Descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int idProducto, String nombreProducto, String Descripcion, double precio, int stock, int idCategoria, String nombreCat, String descripcion) {
        super(idCategoria, nombreCat, descripcion);
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.Descripcion = Descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }  
    
}

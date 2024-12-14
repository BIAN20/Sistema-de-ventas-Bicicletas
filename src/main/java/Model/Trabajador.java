package Model;

public class Trabajador extends Persona {

    private int idTrabajador;
    private double sueldo;
    private String cargo;

    public Trabajador() {
    }

    public Trabajador(int idTrabajador, double sueldo, String cargo) {
        this.idTrabajador = idTrabajador;
        this.sueldo = sueldo;
        this.cargo = cargo;
    }

    public Trabajador(int idTrabajador, double sueldo, String cargo, int idPersona, String nombre, String apellidos, String nroIdentificacion, String direccion, String telefono, String email, estadoPersona estado) {
        super(idPersona, nombre, apellidos, nroIdentificacion, direccion, telefono, email, estado);
        this.idTrabajador = idTrabajador;
        this.sueldo = sueldo;
        this.cargo = cargo;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}

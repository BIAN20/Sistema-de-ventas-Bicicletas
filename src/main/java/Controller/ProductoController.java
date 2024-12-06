package Controller;

import DAO.ProductoDAO;
import Model.CategoriaProducto;
import Model.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

    private final ProductoDAO proDAO = new ProductoDAO();
    private static final String PAG_LISTAR = "/Views/Productos/ListaProductos.jsp";
    private static final String PAG_NUEVO = "/Views/Productos/NuevoProducto.jsp";
    private static final String PAG_ACTUALIZAR = "/Views/Productos/ActualizarProducto.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "actualizar":
                actualizar(request, response);
                break;
            case "buscar":
                buscar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = proDAO.listarProductos();
        request.setAttribute("producto", productos);
        request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            try {
                // Obtener la lista de categorías
                List<CategoriaProducto> categorias = proDAO.obtenerCategorias();
                request.setAttribute("categorias", categorias);
                // Redirigir a la página de registro
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al cargar las categorías", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar las categorías.");
            }
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            try {
                // Obtener datos del formulario
                String nombre = request.getParameter("nombreProducto");
                double precio = Double.parseDouble(request.getParameter("precio"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String descripcion = request.getParameter("descripcion");
                String nombreCat = request.getParameter("categoria");

                // Validar datos (opcional, pero recomendado)
                if (nombre == null || nombre.trim().isEmpty() || nombreCat == null || nombreCat.trim().isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan datos obligatorios.");
                    return;
                }

                // Registrar el producto en la base de datos
                proDAO.registrarProducto(nombre, precio, stock, descripcion, nombreCat);

                // Redirigir a la lista de productos después del registro
                response.sendRedirect("/SistemaDolmarBike/ProductoController?accion=listar");
            } catch (NumberFormatException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al procesar los datos del formulario", ex);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos inválidos en el formulario.");
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al registrar el producto", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al registrar el producto.");
            }
        } else {
            // Si el método no es GET ni POST
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Método no soportado.");
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("hOla");
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) {

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {

    }

}

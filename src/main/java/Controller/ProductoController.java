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
            throws ServletException, IOException, SQLException {
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                List<CategoriaProducto> categorias = proDAO.obtenerCategorias();
                request.setAttribute("categorias", categorias);
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al cargar las categorías", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar las categorías.");
            }
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            try {
                String nombre = request.getParameter("nombreProducto");
                double precio = Double.parseDouble(request.getParameter("precio"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String descripcion = request.getParameter("descripcion");
                String nombreCat = request.getParameter("categoria");
                if (nombre == null || nombre.trim().isEmpty() || nombreCat == null || nombreCat.trim().isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan datos obligatorios.");
                    return;
                }
                proDAO.registrarProducto(nombre, precio, stock, descripcion, nombreCat);
                response.sendRedirect("/SistemaDolmarBike/ProductoController?accion=listar");
            } catch (NumberFormatException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al procesar los datos del formulario", ex);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos inválidos en el formulario.");
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al registrar el producto", ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al registrar el producto.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Método no soportado.");
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProducto = request.getParameter("idProducto");
        if (idProducto != null && !idProducto.isEmpty()) {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String nombreProducto = request.getParameter("nombre");
                String precio = request.getParameter("precio");
                String stock = request.getParameter("stock");
                String descripcion = request.getParameter("descripcion");
                String nombreCat = request.getParameter("categoria");

                if (nombreProducto != null && precio != null && stock != null && descripcion != null && nombreCat != null) {
                    try {
                        proDAO.actualizarProducto(Integer.parseInt(idProducto),
                                nombreProducto,
                                Double.parseDouble(precio),
                                Integer.parseInt(stock),
                                descripcion,
                                nombreCat
                        );
                        response.sendRedirect("ProductoController?accion=listar");
                    } catch (SQLException e) {
                        Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al actualizar el producto", e);
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el producto.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son requeridos.");
                }
            } else { // Si es GET, cargar el formulario con datos
                try {
                    Producto producto = proDAO.obtenerProductoPorId(Integer.parseInt(idProducto));
                    List<CategoriaProducto> categorias = proDAO.obtenerCategorias(); // Obtener las categorías
                    if (producto != null) {
                        request.setAttribute("producto", producto);
                        request.setAttribute("categorias", categorias); // Agregar las categorías al request
                        request.getRequestDispatcher(PAG_ACTUALIZAR).forward(request, response);
                    } else {
                        response.sendRedirect("ProductoController?accion=listar");
                    }
                } catch (SQLException e) {
                    Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, "Error al cargar los datos para actualizar", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar los datos para actualizar.");
                }
            }
        } else {
            response.sendRedirect("ProductoController?accion=listar");
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String criterio = request.getParameter("criterio");
        if (criterio == null || criterio.trim().isEmpty()) {
            request.setAttribute("mensaje", "Por favor ingrese un criterio de búsqueda válido.");
            request.getRequestDispatcher("ProductoController?accion=listar").forward(request, response);
            return;
        }
        List<Producto> productos = proDAO.BuscarProducto(criterio);

        if (productos.isEmpty()) {
            request.setAttribute("mensaje", "No se encontraron productos que coincidan con el criterio de búsqueda.");
        }
        request.setAttribute("producto", productos);
        request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProducto = Integer.parseInt(request.getParameter("id"));
            proDAO.eliminarProducto(idProducto);  // Llamamos a la capa de DAO para eliminar el producto
            response.sendRedirect("/SistemaDolmarBike/ProductoController?accion=listar");

        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(ProductoController.class
                    .getName()).log(Level.SEVERE, "Error al eliminar el producto", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el producto.");
        }
    }

}

package Controller;

import DAO.ProveedorDAO;
import Model.Proveedor;
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

@WebServlet(name = "ProveedorController", urlPatterns = {"/ProveedorController"})
public class ProveedorController extends HttpServlet {

    private final ProveedorDAO provDAO = new ProveedorDAO();
    private static final String PAG_LISTAR = "/Views/Proveedor/ListaProveedor.jsp";
    private static final String PAG_NUEVO = "/Views/Proveedor/NuevoProveedor.jsp";
    private static final String PAG_ACTUALIZAR = "/Views/Proveedor/ActualizarProveedor.jsp";

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
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Proveedor> proveedor = provDAO.listarProveedores();
        request.setAttribute("listaProveedor", proveedor);
        request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            // Obtener parámetros del formulario
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String nroIdentificacion = request.getParameter("nroIdentificacion");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String nombreEmpresa = request.getParameter("nombreEmpresa");
            // Validaciones básicas
            if (nombre == null || nombre.trim().isEmpty()) {
                request.setAttribute("error", "El nombre del proveedor es obligatorio.");
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
                return;
            }
            if (nroIdentificacion == null || nroIdentificacion.trim().isEmpty()) {
                request.setAttribute("error", "El número de identificación es obligatorio.");
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
                return;
            }
            // Validar correo electrónico (opcional)
            if (email != null && !email.trim().isEmpty()) {
                if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                    request.setAttribute("error", "El correo electrónico no tiene un formato válido.");
                    request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
                    return;
                }
            }
            provDAO.registrarProveedor(nombre, apellidos, nroIdentificacion, direccion, telefono, email, nombreEmpresa);
            response.sendRedirect("ProveedorController?accion=listar");
        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String idProveedor = request.getParameter("idProveedor");
        if (idProveedor != null && !idProveedor.isEmpty()) {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                String nroIdentificacion = request.getParameter("nroIdentificacion");
                String email = request.getParameter("email");
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                String nombreEmpresa = request.getParameter("nombreEmpresa");

                if (nombre != null && apellidos != null && nroIdentificacion != null && email != null
                        && direccion != null && telefono != null && nombreEmpresa != null) {
                    try {
                        provDAO.actualizarProveedor(
                                Integer.parseInt(idProveedor),
                                nombre,
                                apellidos,
                                nroIdentificacion,
                                email,
                                direccion,
                                telefono,
                                nombreEmpresa
                        );
                        response.sendRedirect("ProveedorController?accion=listar");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el Proveedor");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son requeridos");
                }
            } else {
                Proveedor proveedor = provDAO.obtenerProveedorPorId(Integer.parseInt(idProveedor));

                if (proveedor != null) {
                    request.setAttribute("proveedor", proveedor);
                    request.getRequestDispatcher(PAG_ACTUALIZAR).forward(request, response);
                } else {
                    response.sendRedirect("ProveedorController?accion=listar");
                }
            }
        } else {
            response.sendRedirect("ProveedorController?accion=listar");
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String criterio = request.getParameter("criterio");
        if (criterio != null && !criterio.isEmpty()) {
            List<Proveedor> proveedores = provDAO.buscarProveedor(criterio);
            request.setAttribute("listaProveedor", proveedores);
            request.setAttribute("criterio", criterio);
            request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
        } else {
            response.sendRedirect("ProveedorController?accion=listar");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProveedor = Integer.parseInt(request.getParameter("id"));
            provDAO.eliminarProveedor(idProveedor); // Llamamos a la capa de DAO para eliminar el producto
            response.sendRedirect("/SistemaDolmarBike/ProveedorController?accion=listar");

        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(ProductoController.class
                    .getName()).log(Level.SEVERE, "Error al eliminar el proveedor", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el proveedor.");
        }
    }
}

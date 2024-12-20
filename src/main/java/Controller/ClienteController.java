package Controller;

import DAO.ClienteDAO;
import Model.Cliente;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

    private final ClienteDAO cliDAO = new ClienteDAO();
    private static final String PAG_LISTAR = "/Views/Clientes/ListaClientes.jsp";
    private static final String PAG_NUEVO = "/Views/Clientes/NuevoCliente.jsp";
    private static final String PAG_ACTUALIZAR = "/Views/Clientes/ActualizarCliente.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
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
        } catch (SQLException e) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error procesando la solicitud");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        int pagina = 1;
        int limite = 10; // Clientes por página

        if (request.getParameter("pagina") != null) {
            pagina = Integer.parseInt(request.getParameter("pagina"));
        }

        int offset = (pagina - 1) * limite; // Desplazamiento
        List<Cliente> clientes = cliDAO.listarClientesConPaginacion(limite, offset);
        int totalClientes = cliDAO.contarClientes();
        int totalPaginas = (int) Math.ceil((double) totalClientes / limite);

        request.setAttribute("listaClientes", clientes);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPaginas);

        request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String nroIdentificacion = request.getParameter("nroIdentificacion");
            String email = request.getParameter("email");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String tipoCliente = request.getParameter("tipoCliente");
            String ruc = request.getParameter("ruc");

            if (nombre == null || nombre.isEmpty() || tipoCliente == null || tipoCliente.isEmpty()) {
                request.setAttribute("error", "El nombre y el tipo de cliente son obligatorios.");
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
                return;
            }
            if ("juridico".equals(tipoCliente)) {
                if (ruc == null || ruc.isEmpty()) {
                    request.setAttribute("error", "El RUC es obligatorio para clientes jurídicos.");
                    request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
                    return;
                }
                cliDAO.registrarCliente(nombre, apellidos, nroIdentificacion, email, direccion, telefono, tipoCliente, ruc);
            } else if ("natural".equals(tipoCliente)) {
                cliDAO.registrarCliente(nombre, apellidos, nroIdentificacion, email, direccion, telefono, tipoCliente, null);
            } else {
                request.setAttribute("error", "El tipo de cliente seleccionado no es válido.");
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
                return;
            }
            response.sendRedirect("ClienteController?accion=listar");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al registrar el cliente: " + e.getMessage());
            request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String idCliente = request.getParameter("idCliente");
        if (idCliente != null && !idCliente.isEmpty()) {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                String nroIdentificacion = request.getParameter("nroIdentificacion");
                String email = request.getParameter("email");
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                if (nombre != null && apellidos != null && nroIdentificacion != null && email != null && direccion != null && telefono != null) {
                    try {
                        cliDAO.actualizarCliente(
                                Integer.parseInt(idCliente),
                                nombre,
                                apellidos,
                                nroIdentificacion,
                                email,
                                direccion,
                                telefono
                        );
                        response.sendRedirect("ClienteController?accion=listar");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el cliente");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son requeridos");
                }
            } else {
                Cliente cliente = cliDAO.obtenerClientePorId(Integer.parseInt(idCliente));

                if (cliente != null) {
                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher(PAG_ACTUALIZAR).forward(request, response);
                } else {
                    response.sendRedirect("ClienteController?accion=listar");
                }
            }
        } else {
            response.sendRedirect("ClienteController?accion=listar");
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String criterio = request.getParameter("criterio");
        if (criterio != null && !criterio.isEmpty()) {
            List<Cliente> clientes = cliDAO.buscarCliente(criterio);
            request.setAttribute("listaClientes", clientes);
            request.setAttribute("criterio", criterio);
            request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
        } else {
            response.sendRedirect("ClienteController?accion=listar");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de cliente no proporcionado");
                return;
            }
            int idCliente = Integer.parseInt(idParam);
            System.out.println("Intentando eliminar el cliente con ID: " + idCliente);
            boolean eliminado = cliDAO.eliminarCliente(idCliente);

            if (eliminado) {
                System.out.println("Cliente eliminado con éxito.");
                response.sendRedirect("ClienteController?accion=listar");
            } else {
                System.out.println("Error al eliminar el cliente.");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error eliminando el cliente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error procesando la solicitud");
        }
    }

}

package Controller;

import DAO.CategoriaDAO;
import Model.CategoriaProducto;
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

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    private final CategoriaDAO catDAO = new CategoriaDAO();
    private static final String PAG_LISTAR = "/Views/CategoriaProducto/ListaCategoria.jsp";
    private static final String PAG_NUEVO = "/Views/CategoriaProducto/NuevaCategoria.jsp";
    private static final String PAG_ACTUALIZAR = "/Views/CategoriaProducto/ActualizarCategoria.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            return;
        }

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
            case "eliminar":
                eliminar(request, response);
                break;
            case "buscar":
                buscar(request, response);
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
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<CategoriaProducto> categorias = catDAO.listarCategorias();
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("/Views/CategoriaProducto/ListaCategoria.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar las categorías.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {

        try {
            String nombreCat = request.getParameter("nombrecat");
            String descripcion = request.getParameter("descripcion");

            if (nombreCat == null || nombreCat.isEmpty() || descripcion == null || descripcion.isEmpty()) {
                request.setAttribute("error", "todos lo campos son obligatorios.");
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
                return;
            }

            CategoriaProducto cat = new CategoriaProducto();
            cat.setNombreCat(nombreCat);
            cat.setDescripcion(descripcion);

            boolean registrado = catDAO.registrarCategoria(cat);
            if (registrado) {
                response.sendRedirect("CategoriaController?accion=listar");
            } else {
                request.setAttribute("error", "Error al registrar la categoría.");
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error", "Se ha producido un error inesperado.");
            try {
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String idCategoria = request.getParameter("idCategoria");
        if (idCategoria != null && !idCategoria.isEmpty()) {
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String nombrecat = request.getParameter("nombrecat");
                String descripcion = request.getParameter("descripcion");
                if (nombrecat != null && descripcion != null) {
                    try {
                        catDAO.actualizarCategoria(
                                Integer.parseInt(idCategoria),
                                nombrecat,
                                descripcion);
                        response.sendRedirect("CategoriaController?accion=listar");
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el cliente");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son requeridos");
                }
            } else {
                CategoriaProducto categoria = catDAO.obtenerCategoriaPorId(Integer.parseInt(idCategoria));
                if (categoria != null) {
                    request.setAttribute("categoria", categoria);
                    request.getRequestDispatcher(PAG_ACTUALIZAR).forward(request, response);
                } else {
                    response.sendRedirect("CategoriiaController?accion=listar");
                }
            }
        } else {
            response.sendRedirect("CategoriaController?accion=listar");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

            boolean eliminado = catDAO.eliminarCategoria(idCategoria);
            if (eliminado) {
                response.sendRedirect("CategoriaController?accion=listar");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar la categoría.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Se ha producido un error inesperado.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String criterio = request.getParameter("criterio");
        if(criterio != null && !criterio.isEmpty()){
            List<CategoriaProducto> categoria = catDAO.buscarCategoria(criterio);
            request.setAttribute("categorias", categoria);
            request.setAttribute("criterio", criterio);
            request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
        }else {
            response.sendRedirect("CategoriaController?accion=listar");
        }
    }
}

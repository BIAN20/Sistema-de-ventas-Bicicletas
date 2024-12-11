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
import java.util.List;

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    private final CategoriaDAO catDAO = new CategoriaDAO();
    private static final String PAG_LISTAR = "/Views/CategoriaProducto/ListaCategoria.jsp";
    private static final String PAG_NUEVO = "/Views/CategoriaProducto/NuevaCategoria.jsp";
    private static final String PAG_ACTUALIZAR = "/Views/CategoriaProducto/ActualizarCategoria.jsp";

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

    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            String nombreCat = request.getParameter("nombrecat");
            String descripcion = request.getParameter("descripcion");

            if (nombreCat == null || nombreCat.isEmpty() || descripcion == null || descripcion.isEmpty()) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher(PAG_ACTUALIZAR).forward(request, response);
                return;
            }
            CategoriaProducto cat = new CategoriaProducto();
            cat.setIdCategoria(idCategoria);
            cat.setNombreCat(nombreCat);
            cat.setDescripcion(descripcion);

            boolean actualizado = catDAO.actualizarCategoria(cat);
            if (actualizado) {
                response.sendRedirect("CategoriaController?accion=listar");
            } else {
                request.setAttribute("error", "Error al actualizar la categoría.");
                request.getRequestDispatcher(PAG_ACTUALIZAR).forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                request.setAttribute("error", "Se ha producido un error inesperado.");
                request.getRequestDispatcher(PAG_ACTUALIZAR).forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
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
    
}

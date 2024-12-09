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

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    private final CategoriaDAO catDAO = new CategoriaDAO();
    private static final String PAG_NUEVO = "/Views/CategoriaProducto/NuevaCategoria.jsp";
    private static final String PAG_ACTUALIZAR = "/Views/CategoriaProducto/ActualizarCategoria.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch (accion) {
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
    }// </editor-fold>

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
                response.sendRedirect("ProductoController?accion=listar");
            } else {
                request.setAttribute("error", "Error al registrar la categoría.");
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();
            // Manejo de errores
            request.setAttribute("error", "Se ha producido un error inesperado.");
            try {
                request.getRequestDispatcher(PAG_NUEVO).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

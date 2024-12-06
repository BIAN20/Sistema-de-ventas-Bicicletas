
package Controller;

import DAO.ProductoDAO;
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
    }// </editor-fold>

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        List<Producto> productos = proDAO.listarProductos();
        request.setAttribute("producto", productos);
        request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
         
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) {
         
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
         
    }

}

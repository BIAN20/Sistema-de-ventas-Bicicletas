package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");

        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario user = userDAO.autenticarUsuario(usuario, contraseña);

        if (user != null) {
            int idRol = user.getIdRol();

            switch (idRol) {
                case 1:
                    response.sendRedirect("Dashboard.jsp");
                    break;
                case 2:
                    response.sendRedirect("DashboardVendedor.jsp");
                    break;
                default:
                    response.sendRedirect("login.jsp?error=3");
                    break;
            }
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

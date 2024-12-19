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
import jakarta.servlet.http.HttpSession;

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
            // Almacenar el usuario y rol en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user.getUser());
            session.setAttribute("rol", user.getIdRol());

            // Redirigir según el rol
            response.sendRedirect("Dashboard.jsp"); // Administrador

        } else {
            response.sendRedirect("login.jsp?error=1"); // Usuario no encontrado
        }
    

}

@Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

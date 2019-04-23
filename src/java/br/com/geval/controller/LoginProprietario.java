package br.com.geval.controller;

import br.com.geval.dao.LoginDAO;
import br.com.geval.dao.ProprietarioDAO;
import br.com.geval.model.Login;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gutemberg
 */
public class LoginProprietario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        
        Login login = new Login();
        login.setUsuario(usuario);
        login.setSenha(senha);
        
        HttpSession sessao = request.getSession();
        
        if(new LoginDAO().validarLogin(login)){
            request.setAttribute("proprietario", 
                    new ProprietarioDAO().retornaProprietario(
                            new ProprietarioDAO().retornaIdProprietarioPorLogin(login)));
            sessao.setAttribute("status_login", "ok");
            request.getRequestDispatcher("./dashboard.jsp").forward(request, response);
        }else{
            response.sendRedirect("./index.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
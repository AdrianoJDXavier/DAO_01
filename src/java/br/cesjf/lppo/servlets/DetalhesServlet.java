package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Contato;
import br.cesjf.lppo.dao.ContatoDAO;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetalhesServlet", urlPatterns = {"/detalhes.html", "/exclui.html"})
public class DetalhesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ContatoDAO dao = new ContatoDAO();
            Contato contato = dao.getById(id);
            request.setAttribute("contato", contato);
            request.getRequestDispatcher("WEB-INF/detalhes-contato.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            response.sendRedirect("contatos.html");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DetalhesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("contatos.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ContatoDAO dao = new ContatoDAO();
            Contato contato = dao.getById(id);

            contato.setNome(request.getParameter("nome"));
            contato.setSobrenome(request.getParameter("sobrenome"));
            contato.setTelefone(request.getParameter("telefone"));
            dao.atualiza(contato);
            response.sendRedirect("contatos.html");
        } catch (NumberFormatException ex) {
            response.sendRedirect("contatos.html");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DetalhesServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("contatos.html");
        }
    }
}

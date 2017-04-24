package br.cesjf.lppo.servlets;


import br.cesjf.lppo.Contato;
import br.cesjf.lppo.dao.ContatoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CriaContatoServlet", urlPatterns = {"/novo.html"})
public class CriaContatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/novo-contato.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Contato novoContato = new Contato();
        novoContato.setNome(request.getParameter("nome"));
        novoContato.setSobrenome(request.getParameter("sobrenome"));
        novoContato.setTelefone(request.getParameter("telefone"));
        
        ContatoDAO dao = new ContatoDAO();
        try {
            dao.cria(novoContato);
        } catch (Exception ex) {
            request.setAttribute("messagem", ex);
            request.getRequestDispatcher("WEB-INF/novo-contato.jsp").forward(request, response);
            return;
        }
        
        response.sendRedirect("contatos.html");
           
    }
}

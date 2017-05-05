package br.cesjf.lppo.servlet;

import br.cesjf.lppo.Contato;
import br.cesjf.lppo.dao.ContatoDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        

        
        try {
            ContatoDAO dao = new ContatoDAO();
            dao.cria(novoContato);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex);
            request.getRequestDispatcher("WEB-INF/novo-contato.jsp");
            return;
        }
        
        response.sendRedirect("contatos.html");
        
    }

}
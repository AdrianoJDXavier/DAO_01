package br.cesjf.lppo.dao;

import br.cesjf.lppo.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private final PreparedStatement opListar;
    private final PreparedStatement opNovo;
    private final PreparedStatement opAtualiza;
    private final PreparedStatement opBuscaPorId;
    

    public ContatoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM contato");
        opBuscaPorId = conexao.prepareStatement("SELECT * FROM contato WHERE id =?");
        opNovo = conexao.prepareStatement("INSERT INTO contato(nome, sobrenome, telefone) VALUES(?,?,?)");
        opAtualiza = conexao.prepareStatement("UPDATE contato SET nome = ?, sobrenome = ?, telefone=? WHERE id = ?");
        
    }

    public List<Contato> listAll() throws Exception {
        try {
            List<Contato> contatos = new ArrayList<>();

            ResultSet resultado = opListar.executeQuery();
            while (resultado.next()) {
                Contato novoContato = new Contato();
                novoContato.setId(resultado.getLong("id"));
                novoContato.setNome(resultado.getString("nome"));
                novoContato.setSobrenome(resultado.getString("sobrenome"));
                novoContato.setTelefone(resultado.getString("telefone"));
                contatos.add(novoContato);
            }

            return contatos;
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os contatos no banco!", ex);
        }
    }

    public Contato getById(Long id) throws Exception {
        try {
            Contato contato = null;
               opBuscaPorId.clearParameters();
               opBuscaPorId.setLong(1, id);
            ResultSet resultado = opBuscaPorId.executeQuery();
            while (resultado.next()) {
                contato  = new Contato();
                contato.setId(resultado.getLong("id"));
                contato.setNome(resultado.getString("nome"));
                contato.setSobrenome(resultado.getString("sobrenome"));
                contato.setTelefone(resultado.getString("telefone"));
            }
            return contato;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o contato no banco!", ex);
        }
    }
    
    public void cria(Contato novoContato) throws Exception {
        try {
            
            opNovo.clearParameters();
            opNovo.setString(1, novoContato.getNome());
            opNovo.setString(2, novoContato.getSobrenome());
            opNovo.setString(3, novoContato.getTelefone());
            opNovo.executeUpdate();


        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir novo contato", ex);
        }
    }
    
       public void atualiza(Contato Contato) throws Exception {
        try {
            
            opAtualiza.clearParameters();
            opAtualiza.setString(1, Contato.getNome());
            opAtualiza.setString(2, Contato.getSobrenome());
            opAtualiza.setString(3, Contato.getTelefone());
            opAtualiza.setLong(4, Contato.getId());
            opNovo.executeUpdate();


        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir novo contato", ex);
        }
        
}
}
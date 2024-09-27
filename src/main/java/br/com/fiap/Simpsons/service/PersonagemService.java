package br.com.fiap.Simpsons.service;
import br.com.fiap.Simpsons.model.Personagem;
import br.com.fiap.Simpsons.dao.PersonagemDao;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Service
public class PersonagemService {
    private PersonagemDao personagemDao;
    public PersonagemService(Connection connection) {
        this.personagemDao = new PersonagemDao(connection);
    }
    public List<Personagem> listar() throws SQLException {
        return personagemDao.listar();
    }
    public void cadastrar(Personagem personagem) throws SQLException {
        personagemDao.cadastrar(personagem);
    }
    public Personagem buscarPorPk(String id) throws SQLException {
        return personagemDao.buscarPorPk(id);
    }
    public List<Personagem> buscarPorNomeNormalizado(String normalizedName) throws SQLException {
        return personagemDao.buscarPorNomeNormalizado(normalizedName);
    }
}
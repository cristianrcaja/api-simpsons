package br.com.fiap.Simpsons.dao;
import br.com.fiap.Simpsons.model.Personagem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PersonagemDao {
    private Connection connection;
    public PersonagemDao(Connection connection) {
        this.connection = connection;
    }
    public List<Personagem> listar() throws SQLException {
        List<Personagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM personagens";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Personagem(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("normalized_name"),
                        rs.getString("gender")
                ));
            }
        }
        return lista;
    }
    public void cadastrar(Personagem personagem) throws SQLException {
        String sql = "INSERT INTO personagens (id, name, normalized_name, gender) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, personagem.getId());
            pstmt.setString(2, personagem.getName());
            pstmt.setString(3, personagem.getNormalized_name());
            pstmt.setString(4, personagem.getGender());
            pstmt.executeUpdate();
        }
    }
    public Personagem buscarPorPk(String id) throws SQLException {
        String sql = "SELECT * FROM personagens WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Personagem(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("normalized_name"),
                        rs.getString("gender")
                );
            }
        }
        return null;
    }
    public List<Personagem> buscarPorNomeNormalizado(String normalizedName) throws SQLException {
        List<Personagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM personagens WHERE normalized_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, normalizedName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(new Personagem(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("normalized_name"),
                        rs.getString("gender")
                ));
            }
        }
        return lista;
    }
}
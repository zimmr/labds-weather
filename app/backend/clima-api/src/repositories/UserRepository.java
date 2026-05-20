package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entities.User;
import utils.MySqlConnection;

public class UserRepository implements IUserRepository {

    @Override
    public void save(User user) {

        String sql = """
            INSERT INTO Usuario (id_user, nome, email, senha, usa_celsius)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setBoolean(5, user.getUseCelsius());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public User get(String id) {

        String sql = """
            SELECT id_user, nome, email, senha, usa_celsius 
            FROM Usuario 
            WHERE id_user = ? 
            LIMIT 1
        """;

        try (Connection conn = MySqlConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean exists(String email) {

        String sql = """
            SELECT 1
            FROM Usuario
            WHERE email = ?
            LIMIT 1
        """;

        try (Connection conn = MySqlConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar logs: " + e.getMessage(), e);
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        String    id      = rs.getString("id_user");
        String    name    = rs.getString("nome");
        String    email   = rs.getString("email");
        String    password   = rs.getString("senha");
        Boolean    usa_celsius = rs.getBoolean("usa_celsius");

        return new User(id, name, email, password, usa_celsius);
    }
}

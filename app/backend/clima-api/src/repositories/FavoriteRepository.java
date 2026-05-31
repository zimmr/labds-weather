package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Favorite;
import utils.MySqlConnection;

public class FavoriteRepository implements IFavoriteRepository {

    @Override
    public List<Favorite> getByUserId(String userId) {

        String sql = """
            SELECT id_fave, user_id, titulo, cidade, estado, pais, latitude, longitude
            FROM Favoritos
            WHERE user_id = ?
            ORDER BY titulo
        """;

        List<Favorite> favorites = new ArrayList<>();

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    favorites.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar favoritos: " + e.getMessage(), e);
        }

        return favorites;
    }

    @Override
    public Favorite getById(String id) {

        String sql = """
            SELECT id_fave, user_id, titulo, cidade, estado, pais, latitude, longitude
            FROM Favoritos
            WHERE id_fave = ?
            LIMIT 1
        """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar favorito: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(Favorite favorite) {

        String sql = """
            INSERT INTO Favoritos (id_fave, user_id, titulo, cidade, estado, pais, latitude, longitude)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, favorite.getId());
            stmt.setString(2, favorite.getUserId());
            stmt.setString(3, favorite.getTitle());
            stmt.setString(4, favorite.getCityName());
            stmt.setString(5, favorite.getState());
            stmt.setString(6, favorite.getCountry());
            stmt.setFloat(7, favorite.getLatitude());
            stmt.setFloat(8, favorite.getLongitude());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar favorito: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Favorite favorite) {

        String sql = """
            UPDATE Favoritos
            SET titulo = ?, cidade = ?, estado = ?, pais = ?, latitude = ?, longitude = ?
            WHERE id_fave = ?
        """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, favorite.getTitle());
            stmt.setString(2, favorite.getCityName());
            stmt.setString(3, favorite.getState());
            stmt.setString(4, favorite.getCountry());
            stmt.setFloat(5, favorite.getLatitude());
            stmt.setFloat(6, favorite.getLongitude());
            stmt.setString(7, favorite.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar favorito: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String id) {

        String sql = """
            DELETE FROM Favoritos
            WHERE id_fave = ?
        """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir favorito: " + e.getMessage(), e);
        }
    }

    @Override
    public int countByUserId(String userId) {

        String sql = """
            SELECT COUNT(*) AS total
            FROM Favoritos
            WHERE user_id = ?
        """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar favoritos: " + e.getMessage(), e);
        }
    }

    private Favorite mapRow(ResultSet rs) throws SQLException {
        String id        = rs.getString("id_fave");
        String userId    = rs.getString("user_id");
        String title     = rs.getString("titulo");
        String cityName  = rs.getString("cidade");
        String state     = rs.getString("estado");
        String country   = rs.getString("pais");
        float  latitude  = rs.getFloat("latitude");
        float  longitude = rs.getFloat("longitude");

        return new Favorite(id, userId, title, cityName, state, country, latitude, longitude);
    }
}

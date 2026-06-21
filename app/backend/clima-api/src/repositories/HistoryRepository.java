package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entities.History;
import utils.MySqlConnection;

public class HistoryRepository implements IHistoryRepository {

    @Override
    public List<History> getByUserId(String userId) {

        String sql = """
            SELECT id_hist, user_id, data_consulta, cidade, estado, pais, latitude, longitude, dados_consulta
            FROM Historico
            WHERE user_id = ?
            ORDER BY data_consulta DESC
        """;

        List<History> historyList = new ArrayList<>();

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    historyList.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar histórico: " + e.getMessage(), e);
        }

        return historyList;
    }

    @Override
    public void save(History history) {

        String sql = """
            INSERT INTO Historico (id_hist, user_id, data_consulta, cidade, estado, pais, latitude, longitude, dados_consulta)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, history.getId());
            stmt.setString(2, history.getUserId());
            stmt.setTimestamp(3, Timestamp.valueOf(history.getSearchDate()));
            stmt.setString(4, history.getCityName());
            stmt.setString(5, history.getState());
            stmt.setString(6, history.getCountry());
            stmt.setFloat(7, history.getLatitude());
            stmt.setFloat(8, history.getLongitude());
            stmt.setString(9, history.getSearchData());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar histórico: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUserId(String userId) {

        String sql = """
            DELETE FROM Historico
            WHERE user_id = ?
        """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao limpar histórico: " + e.getMessage(), e);
        }
    }

    private History mapRow(ResultSet rs) throws SQLException {
        String id           = rs.getString("id_hist");
        String userId       = rs.getString("user_id");
        var    searchDate   = rs.getTimestamp("data_consulta").toLocalDateTime();
        String cityName     = rs.getString("cidade");
        String state        = rs.getString("estado");
        String country      = rs.getString("pais");
        float  latitude     = rs.getFloat("latitude");
        float  longitude    = rs.getFloat("longitude");
        String searchData   = rs.getString("dados_consulta");

        return new History(id, userId, searchDate, cityName, state, country, latitude, longitude, searchData);
    }
}

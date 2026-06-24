package repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dtos.CityCount;
import model.entities.City;
import model.entities.SearchLog;
import utils.MySqlConnection;


public class SearchLogRepository implements ISearchLogRepository {


    @Override
    public ArrayList<SearchLog> getAll() {
        String sql = """
                SELECT id_log, data_consulta,
                       cidade, estado, pais,
                       latitude, longitude
                  FROM Log
                """;

        ArrayList<SearchLog> result = new ArrayList<>();

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar logs: " + e.getMessage(), e);
        }

        return result;
    }

    // Para salvar no banco

    @Override
    public void save(SearchLog log) {
        String sql = """
                INSERT INTO Log (id_log, data_consulta, cidade, estado, pais, latitude, longitude)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, log.getId());
            stmt.setDate(2, Date.valueOf(log.getDate())); 
            stmt.setString(3, log.getCity().getName());
            stmt.setString(4, log.getCity().getState());
            stmt.setString(5, log.getCity().getCountry());
            stmt.setFloat(6, log.getCity().getLatitude());
            stmt.setFloat(7, log.getCity().getLongitude());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar log: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CityCount> getTopCities(LocalDate since, int limit) {
        String sql;
        boolean hasDateFilter = since != null;

        if (hasDateFilter) {
            sql = """
                SELECT cidade, estado, pais, COUNT(*) as total
                  FROM Log
                 WHERE data_consulta >= ?
                 GROUP BY cidade, estado, pais
                 ORDER BY total DESC
                 LIMIT ?
                """;
        } else {
            sql = """
                SELECT cidade, estado, pais, COUNT(*) as total
                  FROM Log
                 GROUP BY cidade, estado, pais
                 ORDER BY total DESC
                 LIMIT ?
                """;
        }

        List<CityCount> result = new ArrayList<>();

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (hasDateFilter) {
                stmt.setDate(1, Date.valueOf(since));
                stmt.setInt(2, limit);
            } else {
                stmt.setInt(1, limit);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new CityCount(
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getInt("total")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cidades mais pesquisadas: " + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public int getTotalSearches(LocalDate since) {
        String sql;
        boolean hasDateFilter = since != null;

        if (hasDateFilter) {
            sql = "SELECT COUNT(*) as total FROM Log WHERE data_consulta >= ?";
        } else {
            sql = "SELECT COUNT(*) as total FROM Log";
        }

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (hasDateFilter) {
                stmt.setDate(1, Date.valueOf(since));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar total de pesquisas: " + e.getMessage(), e);
        }

        return 0;
    }


    private SearchLog mapRow(ResultSet rs) throws SQLException {
        String    id      = rs.getString("id_log");
        LocalDate date    = rs.getDate("data_consulta").toLocalDate();
        String    name    = rs.getString("cidade");
        String    state   = rs.getString("estado");    
        String    country = rs.getString("pais");
        float     lat     = rs.getFloat("latitude");
        float     lon     = rs.getFloat("longitude");

        City city = new City(name, state, country, lat, lon);
        return new SearchLog(id, city, date);       
    }
}


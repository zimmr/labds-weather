package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** util para criar conexão com banco de dados MySQL. */

public final class MySqlConnection {

    private static Connection instance;

    private MySqlConnection() {}

    /*
    Retorna uma conexão ativa com o banco de dados. Pega os dados no config.properties, a principio não teremos users e senhas
    Reutiliza a conexão existente se ainda estiver aberta.
    */
    public static Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            String url      = Config.get("db.url");
            String user     = Config.get("db.user");
            String password = Config.get("db.password");
            instance = DriverManager.getConnection(url, user, password);
        }
        return instance;
    }
}

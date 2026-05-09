package utils;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) throw new RuntimeException("config.properties não encontrado");
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar configurações: " + e.getMessage());
        }
    }

    private Config() {}

    public static String get(String key) {
        String valor = props.getProperty(key);
        if (valor == null) throw new RuntimeException("Chave não encontrada: " + key);
        return valor;
    }
}

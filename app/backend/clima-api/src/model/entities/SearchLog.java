package model.entities;

/* Alterei de LocalDateTime para Localdate, refletindo o tipo no banco */
import java.time.LocalDate;
import java.util.UUID;

public class SearchLog {
    private String id;
    private City city;
    private LocalDate date;      // data_consulta DATE no banco

    public SearchLog(City city) {
        this.id   = UUID.randomUUID().toString();
        this.city = city;
        this.date = LocalDate.now();
    }

    /** Construtor para reconstrução a partir do banco de dados. */
    public SearchLog(String id, City city, LocalDate date) {
        this.id   = id;
        this.city = city;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }
}

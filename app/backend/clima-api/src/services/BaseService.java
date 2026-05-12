package services;

import java.time.Duration;
import java.time.LocalDateTime;

public class BaseService {
    
    private int maxRequests = 5;
    private int interval = 15;
    private LocalDateTime resetTime = LocalDateTime.now();
    private int requests = 0;

    // Regra 3: Controle de execução
    protected void enforceRequestLimit() throws Exception {

        if (Duration.between(resetTime, LocalDateTime.now()).getSeconds() > interval)
        {
            resetTime = LocalDateTime.now();
            requests = 0;
        }

        requests++;

        // Regra 3.2. Limita número máximo de consultas
        // Regra 3.3. Limita número máximo de consultas
        if (requests > maxRequests)
            throw new Exception("Limite de " + maxRequests + " requests a cada " + interval + " segundos atingido.");
    }
}

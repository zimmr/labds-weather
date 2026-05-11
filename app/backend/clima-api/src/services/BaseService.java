package services;

import java.time.Duration;
import java.time.LocalDateTime;

public class BaseService {
    
    private int maxRequests = 5;
    private int interval = 15;
    private LocalDateTime resetTime = LocalDateTime.now();
    private int requests = 0;

    protected void enforceRequestLimit() throws Exception {

        if (Duration.between(resetTime, LocalDateTime.now()).getSeconds() > interval)
        {
            resetTime = LocalDateTime.now();
            requests = 0;
        }

        requests++;
        System.out.println(requests);

        if (requests > maxRequests)
            throw new Exception("Limite de " + maxRequests + " requests a cada " + interval + " segundos atingido.");
    }
}

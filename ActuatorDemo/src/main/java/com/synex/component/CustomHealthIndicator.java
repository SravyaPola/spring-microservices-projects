package com.synex.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom logic for health check
        boolean isHealthy = true;
        if (isHealthy) {
            return Health.up().withDetail("Custom", "Service is running fine").build();
        } else {
            return Health.down().withDetail("Custom", "Service is not healthy").build();
        }
    }
}


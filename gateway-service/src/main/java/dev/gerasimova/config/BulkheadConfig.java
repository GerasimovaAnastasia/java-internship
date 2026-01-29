package dev.gerasimova.config;

import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BulkheadConfig {

    @Bean
    public ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry() {
        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(5)
                .coreThreadPoolSize(2)
                .queueCapacity(5)
                .keepAliveDuration(Duration.ofMillis(20))
                .build();

        Map<String, ThreadPoolBulkheadConfig> configs = new HashMap<>();
        configs.put("default", config);
        configs.put("bookServiceBulkhead", config);

        return ThreadPoolBulkheadRegistry.of(configs);
    }
}

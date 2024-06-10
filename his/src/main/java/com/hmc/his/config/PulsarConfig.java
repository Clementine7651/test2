package com.hmc.his.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PulsarConfig {
    @Value("${pulsar.url}")
    String url;

    @Bean
    public PulsarClient pulsarFactory() throws PulsarClientException {
        PulsarClient client = null;
        try {
            client = PulsarClient.builder()
                    .serviceUrl("pulsar://"+url)
                    .build();
        } catch (PulsarClientException e) {
            log.error("创建PulsarClient发生错误，msg={}", e.getMessage());
            throw e;
        }
        return client;
    }
}
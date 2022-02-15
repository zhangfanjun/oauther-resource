package com.zhang.app1.config.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "ignore")
public class CustomizeIgnoreResourcesConfig {
    String[] resources;
}

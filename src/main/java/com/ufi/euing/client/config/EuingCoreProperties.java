package com.ufi.euing.client.config;


import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@ConfigurationPropertiesScan(basePackages = "com.ufi.euing.client.props")
public class EuingCoreProperties {
}

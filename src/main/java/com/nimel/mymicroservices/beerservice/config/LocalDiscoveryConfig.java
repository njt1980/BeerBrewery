package com.nimel.mymicroservices.beerservice.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("localdiscovery")
@Configuration
@EnableEurekaClient
public class LocalDiscoveryConfig {

}

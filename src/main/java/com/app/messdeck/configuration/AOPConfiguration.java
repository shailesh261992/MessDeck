package com.app.messdeck.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.app.messdeck.aspects" })
public class AOPConfiguration {

}

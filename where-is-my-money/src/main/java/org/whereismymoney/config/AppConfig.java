package org.whereismymoney.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.whereismymoney.config.properties.ApplicationProperties;
import org.whereismymoney.config.properties.VerificationProperties;

@Configuration
@EnableConfigurationProperties({
        ApplicationProperties.class,
        VerificationProperties.class
})
public class AppConfig {
}

package org.whereismymoney.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "verification")
public class VerificationProperties {
    private Integer codeLength = 4;
    private Duration codeExpirationMinutes = Duration.ofMinutes(10);
}

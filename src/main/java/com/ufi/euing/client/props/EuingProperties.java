package com.ufi.euing.client.props;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.math.BigDecimal;

@Data
@ConfigurationProperties(prefix = "euing.service")
@ConstructorBinding
public class EuingProperties {

    Long guichetCode;
    BigDecimal utilisateurCode;
}

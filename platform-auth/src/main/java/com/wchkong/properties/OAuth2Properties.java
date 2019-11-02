package com.wchkong.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;



@Data
@ConfigurationProperties(prefix = "platform.security.oauth2")
public class OAuth2Properties {

    private String jwtSigningKey = "platform";

    private OAuth2ClientProperties[] clients = {};
}

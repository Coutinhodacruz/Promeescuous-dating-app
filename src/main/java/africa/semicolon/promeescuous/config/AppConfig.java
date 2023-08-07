package africa.semicolon.promeescuous.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${mail.api.key}")
    private String mailApiKey;

    public String getMailApiKey() {
        return mailApiKey;
    }
}

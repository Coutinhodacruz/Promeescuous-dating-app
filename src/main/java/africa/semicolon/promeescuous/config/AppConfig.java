package africa.semicolon.promeescuous.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {

    @Value("${mail.api.key}")
    private String mailApiKey;

    @Value("${app.dev.token}")
    private String testToken;

    @Value("${app.base.url}")
    private String baseUrl;

    public String getTestToken() {
        return testToken;
    }

    public String getMailApiKey() {
        return mailApiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }


}

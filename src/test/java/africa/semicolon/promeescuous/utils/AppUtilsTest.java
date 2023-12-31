package africa.semicolon.promeescuous.utils;

import africa.semicolon.promeescuous.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


import static africa.semicolon.promeescuous.utils.AppUtils.generateActivationLink;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j

class AppUtilsTest {



    private  AppConfig appConfig;



    @Test
    public void testGenerateActivationLink(){
        String activationLink = generateActivationLink("localhost:8080","test@email.com");
        log.info("activation link --> {} ", activationLink);
        assertThat(activationLink).isNotNull();
        assertThat(activationLink).contains("localhost:8080/user/activate");
    }

    @Test
    public void generateToken(){
        String email = "test@email.com";
        String token = JwtUtils.generateVerificationToken(email);
        log.info("generated token --> {} ", token);
        assertThat(token).isNotNull();

    }

}
package africa.semicolon.promeescuous.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static africa.semicolon.promeescuous.utils.AppUtils.generateActivationLink;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class AppUtilsTest {

    @Test
    public void testGenerateActivationLink(){
        String activationLink = generateActivationLink("test@email.com");
        log.info("activation link --> {} ", activationLink);
        assertThat(activationLink).isNotNull();
        assertThat(activationLink).contains("http://localhost:8080/activate");
    }

    @Test
    public void generateToken(){
        String email = "test@email.com";
        String token = AppUtils.generateToken(email);
        log.info("generated token --> {} ", token);
        assertThat(token).isNotNull();

    }

}
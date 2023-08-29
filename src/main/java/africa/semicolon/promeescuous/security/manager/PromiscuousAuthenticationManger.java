package africa.semicolon.promeescuous.security.manager;

import africa.semicolon.promeescuous.exception.AuthenticationNotSupportedException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static africa.semicolon.promeescuous.exception.ExceptionMessage.AUTHENTICATION_NOT_SUPPORTED;

@AllArgsConstructor
@Component
public class PromiscuousAuthenticationManger implements AuthenticationManager {



    private final AuthenticationProvider authenticationProvider;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass())) {
            Authentication authenticationResult = authenticationProvider.authenticate(authentication);
            return authenticationResult;
        }
       throw new AuthenticationNotSupportedException(AUTHENTICATION_NOT_SUPPORTED.getMessage());
    }
}

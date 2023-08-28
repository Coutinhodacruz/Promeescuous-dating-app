package africa.semicolon.promeescuous.security.providers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PromiscuousAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;










    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //1. take the username from the request (contained in the authorization ) and use
        // the userDetailsService to look for a user from the Db with that username
        String email = authentication.getPrincipal().toString();
        UserDetails user  =userDetailsService.loadUserByUsername(email);
        // if user from 1 is found, use the passwordEncoder to compare the password from the
        // request to the users password from the Db
        String password = authentication.getCredentials().toString();
        boolean isValidPasswordMatch = passwordEncoder.matches(password, user.getPassword());
        // if the password match request is authenticated
        if (isValidPasswordMatch) {

        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}

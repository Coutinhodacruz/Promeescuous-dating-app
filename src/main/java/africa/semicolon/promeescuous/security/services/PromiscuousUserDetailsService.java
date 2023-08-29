package africa.semicolon.promeescuous.security.services;

import africa.semicolon.promeescuous.model.User;
import africa.semicolon.promeescuous.security.models.SecureUser;
import africa.semicolon.promeescuous.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PromiscuousUserDetailsService implements UserDetailsService {

    private final UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userServices.getUserByEmail(email);
        UserDetails userDetails = new SecureUser(user);
        return userDetails;
    }
}

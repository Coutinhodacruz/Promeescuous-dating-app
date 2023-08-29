package africa.semicolon.promeescuous.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.mapping.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

import static africa.semicolon.promeescuous.utils.AppUtils.getPublicPaths;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class PromiscuousAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (getPublicPaths().contains(request.getServletPath())) filterChain.doFilter(request,response);
        authorize(request);
    }
    private void authorize(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());

       List<SimpleGrantedAuthority> authority = extractClaimsFrom(token);
        SecurityContextHolder.getContext().setAuthentication(claims);

    }

    private List<SimpleGrantedAuthority> extractClaimsFrom(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Claim claim = decodedJWT.getClaim("roles");
        return claim.asList( SimpleGrantedAuthority.class);
    }
}

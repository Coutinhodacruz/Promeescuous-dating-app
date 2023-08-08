package africa.semicolon.promeescuous.utils;

import africa.semicolon.promeescuous.exception.PromiscuousBaseException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


public class AppUtils {

    public static final String APP_NAME = "promisuous inc.";

    public static final String APP_EMAIL = "noreply@promiscuous.africa";

    public static final String WELCOME_MESSAGE = "Welcome to promiscuous inc.";


    public static String generateActivationLink(String email){
        //http://localhost:8080/activate?code=xxxxxxx

        String baseUrl = "localhost:8080";
        String urlActivation = "/activate";
        String querystringPrefix = "?";
        String queryStringKey = "code=";
        String token = generateToken(email);
        String activationLink = baseUrl+urlActivation+querystringPrefix+queryStringKey+token;


        return activationLink;
    }

    public static String generateToken(String email){
        //generate token that has the user's email embedded in it

        String token =JWT.create()
                         .withClaim("users", email)
                         .withIssuer(APP_NAME)
                         .withExpiresAt(Instant.now().plusSeconds(3600))
                         .sign(Algorithm.HMAC512("secret"));
        return token;
    }

    public static String getMailTemplate(){
        Path templateLocation = Paths.get("C:\\Users\\Admin\\promeescuous\\src\\main\\resources\\templates\\index.html");

        try {
            List<String> fileContent = Files.readAllLines(templateLocation);
            String tremplate = String.join("", fileContent);

            return tremplate;

        }catch (IOException exception){
            throw new PromiscuousBaseException(exception.getMessage());
        }

    }

    public static boolean validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("secret"))
                .withIssuer(APP_NAME)
                .withClaimPresence("user")
                .build();
        return verifier.verify(token).getClaim("user") != null;
    }

    public static String extractEmailFrom(String token){
       var claim = JWT.decode(token).getClaim("user");
       return (String) claim.asMap().get("user");

    }
}

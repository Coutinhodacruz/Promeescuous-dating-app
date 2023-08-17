package africa.semicolon.promeescuous.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {

    private String accessToken;
    private String message;
}

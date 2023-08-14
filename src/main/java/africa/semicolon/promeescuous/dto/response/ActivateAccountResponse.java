package africa.semicolon.promeescuous.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ActivateAccountResponse {

    private String message;


    private GetUserResponse user;
}

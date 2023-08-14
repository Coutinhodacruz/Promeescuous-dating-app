package africa.semicolon.promeescuous.dto.response;

import lombok.*;

@ToString

@Builder
@AllArgsConstructor
@Setter
@Getter
public class GetUserResponse {

    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
}

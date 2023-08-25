package africa.semicolon.promeescuous.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FindAllMessagesResponse {


    private List<String> message;
}
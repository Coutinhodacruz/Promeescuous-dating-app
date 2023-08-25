package africa.semicolon.promeescuous.dto.request;

import africa.semicolon.promeescuous.model.Reaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MediaReactionRequest {
    private Reaction reaction;
    private Long mediaId;
    private Long userId;
}
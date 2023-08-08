package africa.semicolon.promeescuous.dto.response;

import lombok.Builder;

@Builder
public class ApiResponse<T> {

    private T data;
}

package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.DirectMessageRequest;
import africa.semicolon.promeescuous.dto.response.DirectMessageResponse;
import africa.semicolon.promeescuous.dto.response.FindAllMessagesResponse;

public interface DirectMessageService {
    DirectMessageResponse send(DirectMessageRequest request, Long senderId, Long receiverId);

    FindAllMessagesResponse findMessagesByIds(long sender, long receiver);
}
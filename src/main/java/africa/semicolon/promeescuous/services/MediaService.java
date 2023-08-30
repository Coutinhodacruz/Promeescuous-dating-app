package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.dto.request.MediaReactionRequest;
import africa.semicolon.promeescuous.dto.response.UploadMediaResponse;
import africa.semicolon.promeescuous.model.Media;
import africa.semicolon.promeescuous.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    UploadMediaResponse uploadProfilePicture(MultipartFile file);
    UploadMediaResponse uploadMedia(MultipartFile file, User user);
    String reactToMedia(MediaReactionRequest mediaReactionRequest);

    Media getMediaByUser(User user);
}

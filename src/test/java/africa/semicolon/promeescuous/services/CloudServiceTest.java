package africa.semicolon.promeescuous.services;

import africa.semicolon.promeescuous.services.cloud.CloudService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static africa.semicolon.promeescuous.utils.AppUtils.TEST_IMAGE_LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CloudServiceTest {

    @Autowired
    CloudService cloudService;

    @Test
    public void testUploadFile(){
        Path path = Paths.get(TEST_IMAGE_LOCATION);
        try(InputStream inputStream = Files.newInputStream(path)){
            MultipartFile file = new MockMultipartFile("testImage", inputStream);
            String response= cloudService.upload(file);
            assertNotNull(response);
            assertThat(response).isNotNull();
        }catch (IOException exception){
            throw new RuntimeException(":(");
        }


    }
}

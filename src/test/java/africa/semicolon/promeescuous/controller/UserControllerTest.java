package africa.semicolon.promeescuous.controller;

import africa.semicolon.promeescuous.dto.request.RegisterUserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();




    @Test
    public void testRegister(){

        try {

            RegisterUserRequest request = new RegisterUserRequest();
            request.setEmail("gojofek702@weishu8.com");
            request.setPassword("password");
            String json = mapper.writeValueAsString(request);
            log.info("request --> {}", json);
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/api/v1/user")
                            .content(json)
                            .contentType(APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

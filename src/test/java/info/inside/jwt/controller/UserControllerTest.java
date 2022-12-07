package info.inside.jwt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.inside.jwt.config.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    JwtProvider jwtProvider;

    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    void createAuthenticationToken() throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        JwtRequest jwtRequest = new JwtRequest("Иван", "1234");
//        String jsonResult = mapper.writeValueAsString(jwtRequest);
////        Authentication authentication = new UsernamePasswordAuthenticationToken("Иван", "1234");
////        String jwtToken = jwtProvider.generateAccessToken(authentication.getName());
//        mockMvc.perform(post("/users/authenticate")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());

    }

}
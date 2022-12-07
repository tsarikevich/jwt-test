package info.inside.jwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.inside.jwt.config.JwtProvider;
import info.inside.jwt.dto.JwtRequest;

import info.inside.jwt.dto.MessageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private JwtProvider jwtProvider;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void createAuthenticationToken() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("Иван", "1234");
        String json = objectMapper.writeValueAsString(jwtRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/users/authenticate")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void saveOrGetMessages() throws Exception {
        MessageDto messageDto = new MessageDto(0, "Иван", "history 5");
        String bearer = "Bearer_" + jwtProvider.generateAccessToken(messageDto.getUserName());
        String json = objectMapper.writeValueAsString(messageDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/users/message")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                                .accept("application/json")
                                .header("Authorization", bearer))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
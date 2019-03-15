package jp.unirita.ccjp.unihorn.api.v1.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.unirita.ccpj.unihorn.api.UnihornApplication;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.UserRepository;
import jp.unirita.ccjp.unihorn.api.WithMockCustomUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UnihornApplication.class)
public class UserControllerTest {

  @Autowired
  private WebApplicationContext context;
  
  @Autowired
  private UserRepository userRepository;
    
  @Autowired
  ObjectMapper mapper;

  private MockMvc mvc;
  
  @BeforeEach
  void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    userRepository.deleteAll();
  }
  
  @Test
  @DisplayName("GET /api/v1/users")
  @WithMockCustomUser
  void testInfo() throws Exception {
    Map<String, Object> json1 = new HashMap<String, Object>();
    json1.put("count", 0);
    json1.put("users", new Object[0]);

    ResultActions actions = mvc.perform(get("/api/v1/users").with(csrf()));
    actions
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(json1)));

    MockHttpServletResponse response = actions.andReturn().getResponse();
    log.debug("{} {}", response.getStatus(), response.getContentAsString());
    
    User user = userRepository.save(new User("id", "name", "alias", "type"));
    
    Map<String, Object> json2 = new HashMap<String, Object>();
    json2.put("count", 1);
    
    json2.put("users", new ArrayList<>());
    ((List<User>) json2.get("users")).add(user);
    mvc.perform(get("/api/v1/users").with(csrf()))
      .andExpect(status().isOk())
      .andExpect(content().json(mapper.writeValueAsString(json2)));
  }
  
  @Test
  @WithMockCustomUser
  void testCreateMe() throws Exception {
      MvcResult res = mvc.perform(post("/api/v1/users/me").with(SecurityMockMvcRequestPostProcessors.csrf())).andReturn();
      System.out.println(res.getResponse());
  }

}

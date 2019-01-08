package jp.unirita.ccjp.unihorn.api.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.unirita.ccpj.unihorn.api.UnihornApplication;
import jp.co.unirita.ccpj.unihorn.api.v1.controller.UserController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UnihornApplication.class)
@RestClientTest
public class UserControllerTest {

  @Autowired
  private UserController userController;
  
  @Test
  @DisplayName("")
  void testInfo() {
    Map<String, Object> map = userController.info();
    assertEquals((long) 0, (long) map.get("count")); 
  }
  
  @Test
  @DisplayNae("")
  void testInfoMe() {
    OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(principal, authorities, authorizedClientRegistrationId);
  }

}

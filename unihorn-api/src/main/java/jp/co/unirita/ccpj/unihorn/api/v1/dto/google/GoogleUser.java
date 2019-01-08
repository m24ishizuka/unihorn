package jp.co.unirita.ccpj.unihorn.api.v1.dto.google;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoogleUser {

  public static User user(OAuth2User principal) {
    Map<String, Object> map = principal.getAttributes();
    User user = new User();
    user.setName((String) map.get("name"));
    
    return user;
  }
}

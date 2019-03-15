package jp.co.unirita.ccpj.unihorn.api;

import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;

@Component
public class GoogleProviderUtility implements ProviderUtility {
  
  @Override
  public String getId(OAuth2User principal) throws Exception {
    if (principal == null) {
      throw new Exception("認可情報が有りません。");
    }
    String id = (String) principal.getAttributes().get("email");
    return id;
  }

  @Override
  public User merge(User user, OAuth2User principal) {
    Map<String, Object> map = principal.getAttributes();
    String id = (String) map.get("email");
    String name = (String) map.get("name");
    user.setId(id);
    user.setName(name);
    return user;
  }
  
}

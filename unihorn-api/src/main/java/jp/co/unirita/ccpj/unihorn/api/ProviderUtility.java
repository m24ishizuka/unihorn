package jp.co.unirita.ccpj.unihorn.api;

import org.springframework.security.oauth2.core.user.OAuth2User;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;

public interface ProviderUtility {
  
  public String getId(OAuth2User principal) throws Exception;
  
  public User merge(User user, OAuth2User principal);

}

package jp.unirita.ccjp.unihorn.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


public class WithCustomSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser>{

  @Override
  public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//    OAuth2User principal = new DefaultOidcUser(authorities, idToken)
    Map<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "user");
    OAuth2User principal = new DefaultOAuth2User(authorities, attributes, "name");
    OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(principal, authorities, "google");
    context.setAuthentication(token);
    return context;
  }

}

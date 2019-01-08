package jp.co.unirita.ccpj.unihorn.api.v1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.unirita.ccpj.unihorn.api.ProviderUtility;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ProviderUtility providerUtility;

  @RequestMapping(method = RequestMethod.GET)
  public Map<String, Object> info() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("count", userService.count());
    return map;
  }

  @RequestMapping(value = "/me", method = RequestMethod.GET)
  public User infoMe(OAuth2AuthenticationToken token) {
    String id = providerUtility.getId(token.getPrincipal());
    User me = userService.getById(id);
    return me;
  }

  @RequestMapping(value = "/me", method = RequestMethod.POST)
  public User createMe(OAuth2AuthenticationToken token, User user) throws Exception {
    return userService.create(providerUtility.merge(user, token.getPrincipal()));
  }

  @RequestMapping(value = "/me", method = RequestMethod.PUT)
  public User updateMe(OAuth2AuthenticationToken token, User user) throws Exception {
    return userService.update(providerUtility.merge(user, token.getPrincipal()));
  }

}

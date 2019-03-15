package jp.co.unirita.ccpj.unihorn.api.v1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;
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
    map.put("users", userService.getAll());
    return map;
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public User createUser(@AuthenticationPrincipal OAuth2User oAuth2User, User user) throws Exception {
    String id = providerUtility.getId(oAuth2User);
    if (userService.getById(id) != null) {
      throw new Exception("存在しているユーザーです。");
    } else {
      return userService.create(providerUtility.merge(user, oAuth2User));
    }
  }

  @RequestMapping(value = "/me", method = RequestMethod.GET)
  public User infoMe(@AuthenticationPrincipal OAuth2User oAuth2User) throws Exception {
    String id = providerUtility.getId(oAuth2User);
    User me = userService.getById(id);
    return me;
  }

  @RequestMapping(value = "/me", method = RequestMethod.POST)
  public User createMe(OAuth2AuthenticationToken authentication, User user) throws Exception {
    System.out.println(authentication.toString());
    return null;
//    userService.create(providerUtility.merge(user, oAuth2User));
  }

  @RequestMapping(value = "/me", method = RequestMethod.PUT)
  public User updateMe(@AuthenticationPrincipal OAuth2User oAuth2User, User user) throws Exception {
    return userService.update(providerUtility.merge(user, oAuth2User));
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User getUser(@PathVariable String id) {
    return userService.getById(id);
  }

}

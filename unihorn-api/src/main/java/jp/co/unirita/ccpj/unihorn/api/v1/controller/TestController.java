package jp.co.unirita.ccpj.unihorn.api.v1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.Board;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.Opinion;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.BoardRepository;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.OpinionRepository;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.UserRepository;
import jp.co.unirita.ccpj.unihorn.api.v1.service.BoardService;
import jp.co.unirita.ccpj.unihorn.api.v1.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

  @RequestMapping(method = RequestMethod.GET)
  public Object user(OAuth2AuthenticationToken token) {
    return token;
  }
  
}

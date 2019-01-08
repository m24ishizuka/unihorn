package jp.co.unirita.ccpj.unihorn.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.unirita.ccpj.unihorn.api.v1.dto.BoardSummaryDTO;
import jp.co.unirita.ccpj.unihorn.api.v1.dto.google.GoogleUser;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.Board;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.service.BoardService;
import jp.co.unirita.ccpj.unihorn.api.v1.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/boards")
public class BoardController {

  @Autowired
  private BoardService boardService;
  
  @Autowired
  private UserService userService;
  
  @RequestMapping(method = RequestMethod.GET)
  public BoardSummaryDTO getSummary(OAuth2AuthenticationToken token,
      @RequestParam(name = "max", defaultValue = "20", required = false) int max, 
      @RequestParam(name = "no", defaultValue = "0", required = false) int pageNo) {

    BoardSummaryDTO dto = boardService.getSummary(max, pageNo);
    return dto;
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Board create(@RequestBody Board source, OAuth2AuthenticationToken token) throws Exception {
    // TODO: check user logged in
    User user = GoogleUser.user(token.getPrincipal());
    
    
    Board board = boardService.create(source, user);
    return board;
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Board getDetail(@PathVariable Long id) {
    
    return boardService.getDetail(id);
  }

}

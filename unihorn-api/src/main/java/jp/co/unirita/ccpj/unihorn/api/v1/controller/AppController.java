package jp.co.unirita.ccpj.unihorn.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.unirita.ccpj.unihorn.api.v1.dto.AppDTO;
import jp.co.unirita.ccpj.unihorn.api.v1.service.AppService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/app")
public class AppController {

  @Autowired
  private AppService service;
  
  @RequestMapping(method = RequestMethod.GET)
  public AppDTO get() {
    log.info("");
    return service.getAppDto();
  }
  
}

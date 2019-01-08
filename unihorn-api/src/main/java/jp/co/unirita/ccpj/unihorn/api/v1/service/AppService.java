package jp.co.unirita.ccpj.unihorn.api.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.unirita.ccpj.unihorn.api.v1.dto.AppDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppService {
  
  @Autowired
  BoardService boardService;

  public AppDTO getAppDto() {
    AppDTO dto = new AppDTO();
    dto.setVersion("1.0.0.00");
//    dto.setBoardCount(boardService.countBoard());
    // TODO
    //    dto.setBoardList(boardList);
    return dto;
  }

}

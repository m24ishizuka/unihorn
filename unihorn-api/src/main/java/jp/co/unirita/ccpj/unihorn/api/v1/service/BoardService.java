package jp.co.unirita.ccpj.unihorn.api.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.unirita.ccpj.unihorn.api.v1.dto.BoardSummaryDTO;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.Board;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.BoardRepository;

@Service
public class BoardService {
  
  @Autowired
  private BoardRepository boardRepository;
  
  @Autowired
  private UserService userService;
  
  public BoardSummaryDTO getSummary(int boardCount, int pageNo) {
    BoardSummaryDTO dto = new BoardSummaryDTO();
    dto.setCount(boardRepository.count());
    
    // TODO
//     dto.setList(list);
    
    return dto;
  }
  
  public Board getDetail(Long id) {
    Board board = boardRepository.findById(id).orElse(null);
    if (board != null) {
      
    }
    return board;
  }
  
  public Board create(Board source, User user) throws Exception {
    if (user == null) {
      throw new Exception(); // TODO
    }
    if (source.getTitle() == null || source.getTitle().length() == 0) {
      throw new Exception(); // TODO
    }
    

    String displayNameType = "alias".equals(source.getDisplayNameType()) ? "alias" : "name";
    source.setDisplayNameType(displayNameType);
    
    Board saved = boardRepository.save(source);
    return saved;
  }

}

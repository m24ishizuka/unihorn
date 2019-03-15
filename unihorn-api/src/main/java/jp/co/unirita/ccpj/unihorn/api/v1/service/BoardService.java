package jp.co.unirita.ccpj.unihorn.api.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jp.co.unirita.ccpj.unihorn.api.v1.dto.BoardSummaryDTO;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.Board;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.BoardRepository;

@Service
public class BoardService {
  
  @Autowired
  private BoardRepository boardRepository;
  
  public Long count() {
    return boardRepository.count();
  }
  
  public List<Board> getAll() {
    return boardRepository.findAll(new Sort(Direction.ASC, "id"));
  }
  
  public List<Board> getMany(int pageNo, int pageSize) {
    return boardRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent();
  }

  public Board create(Board board, User creator) throws Exception {
    if (creator == null) {
      throw new Exception("作成者の情報が有りません。");
    }
    if (board.getTitle() == null || board.getTitle().length() == 0) {
      throw new Exception("タイトルがnullか長さ0の文字列です。");
    }
    board.setId(null);
    board.setUser(creator);
    

    String displayNameType = "alias".equals(source.getDisplayNameType()) ? "alias" : "name";
    source.setDisplayNameType(displayNameType);
    
    Board saved = boardRepository.save(source);
    return saved;
  }

}

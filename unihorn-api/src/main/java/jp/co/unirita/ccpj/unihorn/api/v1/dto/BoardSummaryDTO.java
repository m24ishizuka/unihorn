package jp.co.unirita.ccpj.unihorn.api.v1.dto;

import java.util.List;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.Board;
import lombok.Data;

@Data
public class BoardSummaryDTO {

  private Long count;
  
  private List<Board> list;

}

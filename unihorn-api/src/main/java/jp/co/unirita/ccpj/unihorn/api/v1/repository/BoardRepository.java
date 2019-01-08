package jp.co.unirita.ccpj.unihorn.api.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
  
//  QueryByExampleExecutor<Board> findAll(Example<Board> example, Sort sort);
  
  long count();

}

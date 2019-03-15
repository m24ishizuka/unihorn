package jp.co.unirita.ccpj.unihorn.api.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
  
  public Optional<Board> findById(Long id);
  
  
}

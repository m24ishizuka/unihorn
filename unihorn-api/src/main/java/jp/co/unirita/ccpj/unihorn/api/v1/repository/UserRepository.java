package jp.co.unirita.ccpj.unihorn.api.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  
  public Optional<User> findById(String id);
  
}

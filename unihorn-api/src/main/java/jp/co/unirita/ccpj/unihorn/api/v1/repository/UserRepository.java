package jp.co.unirita.ccpj.unihorn.api.v1.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  public Optional<User> findById(Long id);
  
  public Optional<User> findByName(String name);

}

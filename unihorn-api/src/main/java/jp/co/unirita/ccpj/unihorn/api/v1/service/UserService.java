package jp.co.unirita.ccpj.unihorn.api.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  public Long count() {
    return userRepository.count();
  }
  
  public List<User> getAll() {
    return userRepository.findAll(new Sort(Direction.ASC, "id"));
  }
  
  public List<User> getMany(int pageNo, int pageSize) {
    return userRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent();
  }
  
  public User getById(String id) {
    User user = userRepository.findById(id).orElse(new User());
    return user;
  }
  
  public User create(User user) throws Exception {
    if (user == null) {
      throw new Exception("ユーザーの情報が有りません。");
    }
    if (user.getId() == null || user.getId().length() == 0) {
      throw new Exception("IDが無いか長さが0でした。");
    }
    if (userRepository.findById(user.getId()).orElse(null) != null) {
      throw new Exception("既に存在しているIDです。");
    }
    if (user.getName() == null || user.getName().length() == 0) {
      throw new Exception("名前が無いか長さが0でした。");
    }

    trimAlias(user);
    
    trimDefaultDisplayNameType(user);
    
    return userRepository.save(user);
  }
  
  public User update(User user) throws Exception {
    if (userRepository.findById(user.getId()).orElse(null) == null) {
      throw new Exception("指定のIDと一致するユーザーが存在しません。");
    }
    if (user.getName() == null || user.getName().length() == 0) {
      throw new Exception("名前が無いか長さが0でした。");
    }
    
    trimAlias(user);
    
    trimDefaultDisplayNameType(user);
    
    return userRepository.save(user);
  }
  
  public void remove(User user) throws Exception {
    if (user == null) {
      throw new Exception("ユーザーの情報が有りません。");
    }
    userRepository.deleteById(user.getId());
  }
  
  private void trimAlias(User user) {
    if (user.getAlias() == null || user.getAlias().length() == 0) {
      user.setAlias(null);
    }
  }
  
  private void trimDefaultDisplayNameType(User user) {
    String defaultDisplayNameType =
        ("alias".equals(user.getDefaultDisplayNameType()) && user.getAlias() != null) ?
            "alias" : "name";
    user.setDefaultDisplayNameType(defaultDisplayNameType);
  }

}

package jp.co.unirita.ccpj.unihorn.api.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  public User create(User user) throws Exception {
    if (user == null) {
      throw new Exception("ユーザーの情報が有りません。"); // TODO: to English
    }
    if (user.getId() == null || user.getId().length() == 0) {
      throw new Exception("IDが無いか長さが0でした。");
    }
    if (userRepository.findById(user.getId()).orElse(null) != null) {
      throw new Exception("既に存在しているIDです。"); // TODO exist user X
    }
    if (user.getName() == null || user.getName().length() == 0) {
      throw new Exception("名前が無いか長さが0でした。"); // TODO
    }

    setAlias(user);
    
    setDefaultDisplayNameType(user);
    
    return userRepository.save(user);
  }
  
  public User update(User user) throws Exception {
    if (userRepository.findById(user.getId()).orElse(null) == null) {
      throw new Exception("");
    }
    
    setAlias(user);
    
    setDefaultDisplayNameType(user);
    
    return userRepository.save(user);
  }
  
  public Long count() {
    return userRepository.count();
  }
  
  public User getById(String id) {
    User user = userRepository.findById(id).orElse(new User());
    return user;
  }
  
  private void setAlias(User user) {
    if (user.getAlias() == null || user.getAlias().length() == 0) {
      user.setAlias(null);
    }
  }
  
  private void setDefaultDisplayNameType(User user) {
    String defaultDisplayNameType =
        ("alias".equals(user.getDefaultDisplayNameType()) && user.getAlias() != null) ?
            "alias" : "name";
    user.setDefaultDisplayNameType(defaultDisplayNameType);
  }

}

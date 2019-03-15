package jp.unirita.ccjp.unihorn.api.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.unirita.ccpj.unihorn.api.UnihornApplication;
import jp.co.unirita.ccpj.unihorn.api.v1.entity.User;
import jp.co.unirita.ccpj.unihorn.api.v1.repository.UserRepository;
import jp.co.unirita.ccpj.unihorn.api.v1.service.UserService;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@ExtendWith(SpringExtension.class) 
@SpringBootTest(classes = UnihornApplication.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;
  
  @Autowired
  private UserRepository userRepository;
  
  @BeforeEach
  void setup() {
    userRepository.deleteAll();
  }
  
  @Test
  @DisplayName("")
  void testCount() {
    assertEquals(new Long(0), userService.count());
    
    userRepository.save(new User("s", "s", "s", "s"));
    assertEquals(new Long(1), userService.count());
  }
  
  @Test
  @DisplayName("")
  void testGetMany() {
    userRepository.save(new User("08", "s", "s", "s"));
    userRepository.save(new User("01", "s", "s", "s"));
    userRepository.save(new User("06", "s", "s", "s"));
    userRepository.save(new User("05", "s", "s", "s"));
    userRepository.save(new User("02", "s", "s", "s"));
    userRepository.save(new User("03", "s", "s", "s"));
    userRepository.save(new User("04", "s", "s", "s"));
    userRepository.save(new User("10", "s", "s", "s"));
    userRepository.save(new User("07", "s", "s", "s"));
    userRepository.save(new User("09", "s", "s", "s"));
    
    
    List<User> userList = userService.getMany(0, 2);
    assertEquals("01", userList.get(0).getId());
    assertEquals(2, userList.size());
    
    List<User> userList2 = userService.getMany(1, 2);
    assertEquals("03", userList2.get(0).getId());
    assertEquals(2, userList2.size());
    
    List<User> userList3 = userService.getMany(1, 5);
    assertEquals("06", userList3.get(0).getId());
    assertEquals(5, userList3.size());
    
    assertEquals(0, userService.getMany(10, 2).size());
  }

  @Test
  @DisplayName("ユーザー情報自体がNULL")
  void testCreateWithNull() {
    User user = null;
    Throwable e = assertThrows(Exception.class, () -> userService.create(user));
    assertEquals("ユーザーの情報が有りません。", e.getMessage());
  }
  
  @Test
  @DisplayName("IDがNULL")
  void testCreateWithNullId() {
    User user = new User(null, null, null, null);
    Throwable e = assertThrows(Exception.class, () -> userService.create(user));
    assertEquals("IDが無いか長さが0でした。", e.getMessage());
  }
  
  @Test
  @DisplayName("IDが長さ0")
  void testCreateWithZeroLengthId() {
    User user = new User("", null, null, null);
    Throwable e = assertThrows(Exception.class, () -> userService.create(user));
    assertEquals("IDが無いか長さが0でした。", e.getMessage());
  }
  
  @Test
  @DisplayName("IDが既存")
  void testCreateWithExistedId() throws Exception {
    User src = new User("1@example.com", "1", null, null);
    userService.create(src);
    
    User user = new User("1@example.com", "2", null, null);
    Throwable e = assertThrows(Exception.class, () -> userService.create(user));
    assertEquals("既に存在しているIDです。", e.getMessage());
  }
  
  @Test
  @DisplayName("名前がNULL")
  void testCreateWithNullName() {
    User user = new User("2@example.com", null, null,  null);
    Throwable e = assertThrows(Exception.class, () -> userService.create(user));
    assertEquals("名前が無いか長さが0でした。", e.getMessage());
  }
  
  @Test
  @DisplayName("名前が長さ0")
  void testCreateWithZeroLengthName() {
    User user = new User("2@example.com", "", null, null);
    Throwable e = assertThrows(Exception.class, () -> userService.create(user));
    assertEquals("名前が無いか長さが0でした。", e.getMessage());
  }
  
  @Test
  @DisplayName("別名がNULL")
  void testCreateWithNoAlias() throws Exception {
    User src = new User("2@example.com", "2", null, null);
    User user = userService.create(src);
    assertEquals("2", user.getName());
    assertEquals(null, user.getAlias());
    assertEquals("name", user.getDefaultDisplayNameType());
  }
  
  @Test
  @DisplayName("別名が長さ0")
  void testCreateWithZeroLengthAlias() throws Exception {
    User src = new User("3@example.com", "3", "", null);
    User user = userService.create(src);
    assertEquals("3", user.getName());
    assertEquals(null, user.getAlias());
    assertEquals("name", user.getDefaultDisplayNameType());
  }
  
  @Test
  @DisplayName("デフォルト表示名種類が間違っている")
  void testCreateWithIllegalDefaultDisplayNameType() throws Exception {
    User src = new User("4@example.com", "4", "", "dummy");
    User user = userService.create(src);
    assertEquals("4", user.getName());
    assertEquals("name", user.getDefaultDisplayNameType());
  }
  
  @Test
  @DisplayName("デフォルト表示名種類が別名だが別名がNULL")
  void testCreateWithDefaultDisplayNameTypeIsAliasAndNullAlias() throws Exception {
    User src = new User("5@example.com", "5", null, "alias");
    User user = userService.create(src);
    assertEquals(null, user.getAlias());
    assertEquals("name", user.getDefaultDisplayNameType());
  }
  
  @Test
  @DisplayName("デフォルト表示名種類が別名だが別名が長さ0")
  void testCreateWithDefaultDisplayNameTypeIsAliasAndZeroLengthAlias() throws Exception {
    User src = new User("6@example.com", "6", "", "alias");
    User user = userService.create(src);
    assertEquals(null, user.getAlias());
    assertEquals("name", user.getDefaultDisplayNameType());
  }
  
  @Test
  @DisplayName("デフォルト表示名種類が名前")
  void testCreateWithDefaultDisplayNameTypeIsName() throws Exception {
    User src = new User("7@example.com", "7", "nana", "name");
    User user = userService.create(src);
    assertEquals("nana", user.getAlias());
    assertEquals("name", user.getDefaultDisplayNameType());
  }
  
  @Test
  @DisplayName("デフォルト表示名種類が別名")
  void testCreateWithDefaultDisplayNameIsAlias() throws Exception {
    User src = new User("8@example.com", "8", "hachi", "alias");
    User user = userService.create(src);
    assertEquals("8", user.getName());
    assertEquals("hachi", user.getAlias());
    assertEquals("alias", user.getDefaultDisplayNameType());
  }
  
}

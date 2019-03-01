package jp.unirita.ccjp.unihorn.api.v1.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.co.unirita.ccpj.unihorn.api.UnihornApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UnihornApplication.class)
public class UserControllerTest {

  @Autowired
  private WebApplicationContext context;
  
  private MockMvc mvc;
  
  @BeforeEach
  void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }
  
  @Test
  void ok() {
    mvc.perform(get("/").with(authentication(new Authentication() {
      
      @Override
      public String getName() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public boolean isAuthenticated() {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public Object getPrincipal() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public Object getDetails() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public Object getCredentials() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
      }
    })))
  }

}

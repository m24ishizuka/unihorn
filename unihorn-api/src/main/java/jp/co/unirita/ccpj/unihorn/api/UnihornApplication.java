package jp.co.unirita.ccpj.unihorn.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class UnihornApplication {

  public static void main(String[] args) {
    SpringApplication.run(UnihornApplication.class, args);
    
    log.info("start");
  }

}

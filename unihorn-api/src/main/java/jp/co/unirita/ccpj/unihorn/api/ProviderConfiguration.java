package jp.co.unirita.ccpj.unihorn.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfiguration {

  @Bean
  public ProviderUtility providerUtility() {
    return new GoogleProviderUtility();
  }

}

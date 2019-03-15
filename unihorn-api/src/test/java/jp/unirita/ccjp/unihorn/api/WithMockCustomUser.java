package jp.unirita.ccjp.unihorn.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomSecurityContextFactory.class)
public @interface WithMockCustomUser {

  String username() default "testuser";
  String name() default "Test User";

}

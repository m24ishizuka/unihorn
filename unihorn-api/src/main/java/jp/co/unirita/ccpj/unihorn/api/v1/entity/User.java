package jp.co.unirita.ccpj.unihorn.api.v1.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  private String id;

  private String name;
  
  private String alias;
  
  private String defaultDisplayNameType;

}

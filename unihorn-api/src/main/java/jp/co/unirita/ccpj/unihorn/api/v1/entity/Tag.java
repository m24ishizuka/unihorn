package jp.co.unirita.ccpj.unihorn.api.v1.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(value = Include.NON_NULL)
public class Tag implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

}

package jp.co.unirita.ccpj.unihorn.api.v1.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(value = Include.NON_NULL)
public class Opinion implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private String content;
  
  private Date created;
  
  private String displayNameType;
  
  @ManyToOne
  private Board board;
  
  @OneToOne
  private User user;

}

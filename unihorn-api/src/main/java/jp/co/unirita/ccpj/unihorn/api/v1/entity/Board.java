package jp.co.unirita.ccpj.unihorn.api.v1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(value = Include.NON_NULL)
public class Board implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;
  
  private String description;
  
  private Date created;
  
  private Date updated;
  
  private String displayNameType;

  @OneToOne
  private User user;
  
  @OneToMany
  private List<Opinion> opinionList;

}

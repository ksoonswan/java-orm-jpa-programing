package jpabasic.ex1hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Id
  private Long id;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

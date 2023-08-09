package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Team extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "team_id")
  private Long id;

  private String name;


}

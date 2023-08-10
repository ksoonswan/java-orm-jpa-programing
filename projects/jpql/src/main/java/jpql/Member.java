package jpql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Member {

  @Id
  @GeneratedValue
  private Long id;

  private String userName;

  private int age;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

}

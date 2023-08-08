package jpabasic.ex1hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String userName;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  public void changeTeam(Team team) {
    this.team = team;
    team.getMembers().add(this);
  }
}

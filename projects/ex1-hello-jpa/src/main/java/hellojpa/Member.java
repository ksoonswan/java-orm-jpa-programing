package hellojpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

  @Embedded
  private Address address;

  @ElementCollection
  @CollectionTable(name = "favorite_food",
      joinColumns = @JoinColumn(name = "member_id"))
  @Column(name = "food_name")
  private Set<String> favoriteFoods = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "address",
      joinColumns = @JoinColumn(name = "member_id"))
  private List<Address> addressHistory = new ArrayList<>();


}

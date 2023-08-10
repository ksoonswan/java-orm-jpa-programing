package jpql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private int price;

  private int stockAmount;
}

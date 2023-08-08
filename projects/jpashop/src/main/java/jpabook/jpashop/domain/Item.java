package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Item {

  @Id
  @GeneratedValue
  @Column(name = "item_id")
  private Long id;

  private String name;

  private int price;

  private int stockQuantity;
}

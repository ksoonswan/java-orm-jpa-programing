package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Delivery {

  @Id
  @GeneratedValue
  private Long id;

  private String city;

  private String street;

  private String zipcode;

  private DeliveryStatus status;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
  private Order order;
}

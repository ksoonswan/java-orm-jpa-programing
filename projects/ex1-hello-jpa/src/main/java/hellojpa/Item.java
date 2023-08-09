package hellojpa;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // 조인 테이블 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 각각의 테이블을 모두 만듬 => 쓰면안되는 전략
@DiscriminatorColumn // 구분 컬럼 생성
public abstract class Item {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private int price;
}

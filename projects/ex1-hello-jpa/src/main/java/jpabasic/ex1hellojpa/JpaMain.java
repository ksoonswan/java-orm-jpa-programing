package jpabasic.ex1hellojpa;

import hellojpa.Address;
import hellojpa.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      Member member = new Member();
      member.setUserName("member1");
      member.setAddress(new Address("homeCity", "street", "110-2222"));

      member.getFavoriteFoods().add("치킨");
      member.getFavoriteFoods().add("족발");
      member.getFavoriteFoods().add("피자");

      member.getAddressHistory().add(new Address("old1", "street", "110-2222"));
      member.getAddressHistory().add(new Address("old2", "street", "110-2222"));

      em.persist(member);

      em.flush();
      em.clear();

      Member member1 = em.find(Member.class, member.getId());

      member1.setAddress(new Address("newCity", member1.getAddress().getStreet(), member1.getAddress().getZipcode()));

      // 치킨 -> 한식
      member1.getFavoriteFoods().remove("치킨");
      member1.getFavoriteFoods().add("한식");

      member1.getAddressHistory().remove(new Address("old1", "street", "110-2222"));
      member1.getAddressHistory().add(new Address("newCity", "street", "110-2222"));

      System.out.println();

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    em.close();
    emf.close();

  }


}

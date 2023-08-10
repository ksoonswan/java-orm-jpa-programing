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
      Address address = new Address("city", "street", "1001000");

      Member member = new Member();
      member.setUserName("member1");
      member.setAddress(address);
      em.persist(member);

      Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
      member.setAddress(address2);

      //

//      member.getAddress().setCity("newCity");

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

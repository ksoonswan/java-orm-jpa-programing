package jpabasic.ex1hellojpa;

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
      // 비영속
      Member member = new Member();
      member.setId(101L);
      member.setName("HelloJPAPA");

      // 영속
      System.out.println("========111111=========");
      em.persist(member);
      System.out.println("========222222=========");

      Member member1 = em.find(Member.class, 100L);
      Member member2 = em.find(Member.class, 100L);

      System.out.println("member1>>>>>>>" + member1.getId());
      System.out.println("member1==member2: " + (member1 == member2));

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

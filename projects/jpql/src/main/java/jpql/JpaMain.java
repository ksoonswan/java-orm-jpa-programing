package jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jpql.entity.Member;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      Member member = new Member();
      member.setUserName("member1");
      member.setAge(100);
      em.persist(member);

      TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
      TypedQuery<String> query2 = em.createQuery("select m.userName from Member m", String.class);
      Query query3 = em.createQuery("select m.userName, m.age from Member m");

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

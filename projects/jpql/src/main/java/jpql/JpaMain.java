package jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;
import jpql.entity.Member;
import jpql.entity.Team;

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

      List<Team> resultList = em.createQuery("select m.team from Member m ", Team.class)
          .setFirstResult(0)
          .setMaxResults(10)
          .getResultList();

      for (Team member1 : resultList) {
        System.out.println("member: " + member1.getName());
      }

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

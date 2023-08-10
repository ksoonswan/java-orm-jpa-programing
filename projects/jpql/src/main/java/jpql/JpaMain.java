package jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
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

      TypedQuery<Member> query = em.createQuery("select m from Member m WHERE m.userName = :userName", Member.class);
      query.setParameter("userName", "member1");
      List<Member> resultList = query.getResultList();

      for (Member member1 : resultList) {
        System.out.println("member: " + member1.getUserName());
      }

      // 이거는 데이터가 없거나, 두개 이상있으면 오류가 나기 때문에 권장하지 않는다.
      Member resultOne = query.getSingleResult();
      System.out.println("Single Member: " + resultOne.getUserName());

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

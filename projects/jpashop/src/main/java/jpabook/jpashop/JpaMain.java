package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import jpabook.jpashop.domain.Member;


public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
//      Member newMember = new Member();
//      newMember.setName("kim");
//      em.persist(newMember);

      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Member> query = cb.createQuery(Member.class);

      Root<Member> m = query.from(Member.class);

      CriteriaQuery<Member> cq = query.select(m)
          .where(cb.equal(m.get("name"), "kim"));

      List<Member> resultList = em.createQuery(cq)
          .getResultList();

      for (Member member : resultList) {
        System.out.println("=============");
        System.out.println(member.getName());
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

package jpabasic.ex1hellojpa;

import hellojpa.Member;
import hellojpa.Team;
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

      Member member1 = new Member();
      member1.setUserName("member1");
      em.persist(member1);

      Member member2 = new Member();
      member1.setUserName("member2");
      em.persist(member2);

      em.flush();
      em.clear();

      Member m1 = em.find(Member.class, member1.getId());
      Member m2 = em.find(Member.class, member2.getId());

      System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));

      Member reference = em.getReference(Member.class, member1.getId());
      System.out.println("reference = " + reference.getClass());
      System.out.println("m1 = reference: " + (m1.getClass() == reference.getClass()));

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    em.close();
    emf.close();

  }

  private static void printMember(Member member) {
    System.out.println(">>>>>>>>>>>>>>>>>");
    String userName = member.getUserName();
    System.out.println("userName: " + userName);
  }

  private static void printMemberTeam(Member member) {
    System.out.println(">>>>>>>>>>>>>>>>>");
    String userName = member.getUserName();
    System.out.println("userName: " + userName);

    Team team = member.getTeam();
    System.out.println("team: " + team.getName());
  }

}

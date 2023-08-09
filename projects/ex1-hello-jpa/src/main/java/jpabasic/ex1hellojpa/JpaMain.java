package jpabasic.ex1hellojpa;

import hellojpa.Member;
import hellojpa.Team;
import java.util.List;
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
      Team team = new Team();
      team.setName("TeamA");
      em.persist(team);

      Member member1 = new Member();
      member1.setUserName("member1");
      member1.setTeam(team);
      em.persist(member1);

      em.flush();
      em.clear();

//      Member m = em.find(Member.class, member1.getId());
//      System.out.println("m = " + m.getTeam().getClass());

      List<Member> members = em.createQuery("select m from Member m left join fetch m.team", Member.class)
          .getResultList();

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

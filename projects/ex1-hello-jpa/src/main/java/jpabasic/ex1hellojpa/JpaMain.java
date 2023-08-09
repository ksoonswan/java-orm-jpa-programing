package jpabasic.ex1hellojpa;

import hellojpa.Child;
import hellojpa.Member;
import hellojpa.Parent;
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

      Child child1 = new Child();
      Child child2 = new Child();

      Parent parent = new Parent();
      parent.addChild(child1);
      parent.addChild(child2);

      em.persist(parent);

      em.flush();
      em.clear();

      Parent findParent = em.find(Parent.class, parent.getId());
      findParent.getChildList().remove(0);

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

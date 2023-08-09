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

      Member member = new Member();
      member.setUserName("hello");

      em.persist(member);

      em.flush();
      em.clear();

      //
//      Member findMember = em.find(Member.class, member.getId());

      // getReference는 이 소스를 사용할때가아닌, findMember를 직접적으로 사용할때 쿼리가 나간다.
      //  => 프록시에 데이더가 없기 때문에 디비를 통해서 엔티티를 가져온다음 보여준다고 생각하면 된다.
      // 프록시의 예시이다.
      Member findMember = em.getReference(Member.class, member.getId());

      //findMember >>>>>> class hellojpa.Member$HibernateProxy$BE8JyB2c
      System.out.println("findMember >>>>>> " + findMember.getClass());
      printMember(findMember);

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

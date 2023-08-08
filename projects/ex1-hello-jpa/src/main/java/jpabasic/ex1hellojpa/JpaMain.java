package jpabasic.ex1hellojpa;

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

      // 저장
      Team team = new Team();
      team.setName("TeamA");
      // 역방향(주인이 아닌 방향)만 연관관계 설정
//      team.getMembers().add(member);
      em.persist(team);

      Member member = new Member();
      member.setUserName("member1");
//      member.changeTeam(team);
      em.persist(member);

      team.addMember(member);

      // 결론은 양방향 매핑을 할때는 양방향 모두 값을 세팅해주는 것이 중요하다.
//      team.getMembers().add(member);

//      em.flush();
//      em.clear();

      Team findTeam = em.find(Team.class, team.getId());
      List<Member> members = findTeam.getMembers();

      for (Member m : members) {
        System.out.println("Member = " + m.getUserName());
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

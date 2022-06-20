package hellojpa;

import hellojpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class PersistenceStudyMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            //비영속
            //persist를 사용하지 않고 객체만 생성한 상태
            Member member = new Member();
            member.setId(105L);
            member.setName("persistentStudyMember1");

            //영속
            /*
             * jpa에는 영속성 컨텍스트라는 개념이 있음
             * 영속이라는 것은 영속성 컨텍스트에 등록된 상태를 의미함
             * 영속성 컨텍스트에 등록되면 1차 캐시가 되고 지연쓰기 저장소에 sql를 저장함
             * 또한 flsuh가 나가는 시점에 데이터베이스에 반영comit시에는 자동으로 flush가 나가게 되어 있음
             * */
//            Member member1 = new Member();
//            member1.setId(105L);
//            member1.setName("persistentStudyMember1");
//
//            //persist 함수로 앤티티 메니저는 member1을 영속성 컨텍스트에 반영하여 영속상태로 만듬
//            entityManager.persist(member1);
//
//            //1차캐시에서 조회되는 상태에서 조회시 영속성 컨텍스트에서 조회 해 옴
//            //아래의 경우 쿼리가 나가지 않음
//            Member findMember = entityManager.find(Member.class, 105L);
//
//            entityManager.flush();
//
//            //find를 통해 찾는데 영속성 컨텍스트에 없을 시 데이터베이스에서 찾고 영속성 컨텍스트에 등록하여 영속상태로 만듬
//            //아래의 경우 쿼리가 나감
//            Member findMember2 = entityManager.find(Member.class, 105L);

            //flush
            /*실제로 데이터베이스에 반영 되는 상황 수정된 엔티티가 있다면 지연쓰기 sql저장소에 등록
             * flush가 되는 상황은 트렌젝션 커밋,엔티티 매니저 flush,jpql 사용시 이다.
             * */
//            entityManager.flush();
//
//            Member jpqlTestMember1 = new Member();
//            jpqlTestMember1.setId(201L);
//            jpqlTestMember1.setName("jpqlTestMember1");
//            Member jpqlTestMember2 = new Member();
//            jpqlTestMember2.setId(202L);
//            jpqlTestMember2.setName("jpqlTestMember2");
//            Member jpqlTestMember3 = new Member();
//            jpqlTestMember3.setId(203L);
//            jpqlTestMember3.setName("jpqlTestMember3");
//
//            entityManager.persist(jpqlTestMember1);
//            entityManager.persist(jpqlTestMember2);
//            entityManager.persist(jpqlTestMember3);
//
//            //jpql사용시 flush가 실행되어 상단의 persist에서 저장된 쿼리가 현시점에 전송됨
//            List<Member> resultList = entityManager.createQuery("select m from Member as m", Member.class).getResultList();
//            for (Member result : resultList) {
//                System.out.println("result = " + result);
//            }
            //준영속
        /*
        준영속이란 영속성컨텍스트에서 분리된 상태이다
        영속 상태와는 다르게 영속에서 있던 이점을 사용 할 수 없다
        준영속 상태가 되는 경우는: 앤티티매니저의 detach,앤티티매니저 clear,앤티티매니저 close 상태일때이다.
        * */
//            Member detachMember = new Member();
//            detachMember.setId(301L);
//            detachMember.setName("detachMember");
//
//            //영속상태로 만들기
//            entityManager.persist(detachMember);
//
//            //준영속으로 만듬
//            entityManager.detach(detachMember);

            //삭제
            Member removeMember = new Member();
            removeMember.setId(401L);
            removeMember.setName("removeMember");
            entityManager.persist(removeMember);

            entityManager.remove(removeMember);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}

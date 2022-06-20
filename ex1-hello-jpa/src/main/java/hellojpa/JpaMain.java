package hellojpa;

import hellojpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //앤티티 매니저 팩토리 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        //앤티티 매니저 생성 *앤티티 메니저는 디비랑 하나의 커넥션을 만듬
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //jpa는 트렌젝션을 설정해줘야만 동작
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
            //저장

//            Member member1 = new Member();
//            member1.setId(1L);
//            member1.setName("test1");
//
//            Member member2=new Member();
//            member2.setId(2L);
//            member2.setName("test2");
//
//            entityManager.persist(member1);
//            entityManager.persist(member2);

            //찾기

//            Member findMember = entityManager.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember);

            //삭제

//            Member findMember = entityManager.find(Member.class, 1L);
//            entityManager.remove(findMember);

            //수정
            //수정은 persist가 필요 없음
//            Member findMember = entityManager.find(Member.class, 2L);
//            findMember.setName("updateTest2");

            //jpql
            //데이터베이스가 아닌 엔티티(객채)를 대상으로 쿼리 조회
//            List<Member> resultList = entityManager.createQuery("select m from Member as m", Member.class).getResultList();
//
//            for(Member member:resultList){
//                System.out.println("member = " + member);
//            }

            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally {
            entityManager.close();

        }

        entityManagerFactory.close();
    }
}

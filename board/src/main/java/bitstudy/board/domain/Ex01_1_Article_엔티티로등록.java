package bitstudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 여기서는 gradle 방식으로 코드를 짠다 -> JPA나 Lombok 같은 디팬던시들을 이용해서 코드 짜기
 *
 * @Table : 엔티티와 매핑할 테이블 지정, 생략 시 매핑할 엔티티 이름을 테이블이름으로 사용
 * ex> @Index(name="원하는명칭", columnlist="db사용 컬럼명")
 *
 * @Index : db 인덱스는 추가 쓰기 같은 저장공간을 미리 잡는 어노테이션
 */
@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy")
})
public class Ex01_1_Article_엔티티로등록 {

//    PK키라는거
//    private Long id 의 경우 auto_increment처럼 db에 값 저장 후 그 후에 값을 구하는 것을 기본 키 전략
//    @ID만 사용할 경우 키를 직접 할당해야하고 // 아닐 경우 DB가 생성하는 값을 사용하기 위해서는 @GenerateValue(strategy=GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    @Setter
    private String title;

    @Column(nullable = false, length = 10000)
    @Setter
    private String content;

    @Setter
    private String hashtag;


    // 양뱡향 바인딩(Ex01_2 의 연관관계 매핑 부분 끝나고 수행
    @OrderBy("id") //-> 양방향 바인딩의 정렬기준은 id로 하겠다?
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // -> mappedBy로 양방향 바인딩의 이름을 지정
//    ToString.Exclude -> Circle reference 이슈가 생기는거 방지 -> @Tosting이 모든 필드를 로그에 찍고 Set<Ex01_2_ArticleCommnet>를 찍으려고 하고 article 을 보는 순간 다시 article에 @ToString이 동작한다 -> 서로가 서로를 계속 호출하는 것을 방지
    @ToString.Exclude
    private final Set<Ex01_2_ArticleComment> articleComment = new LinkedHashSet<>();

    /**    factory method pattern(정적 팩토리 매서드)
     -> 객체 생성 역할을 하는 클래스 매서드. of 매서드를 이용 직접적으로 생성자를 사용해서 객체 생성
     장점 : static 이기 때문에 new를 이용하지 않고 생성자 생성가능
     return으로 상속을 사용할때 값을 확인 가능(하위 자료형 객체로 반환 가능
     객체 생성을 캡슐화 가능
     */

//  메타데이터
//  jpa auditing : JPA에서 자동으로 세팅하게 해주는 설정 -> config파일 필요 -> JpaConfig
//    아래의 4개중 생성일시나 수정일시는 알수 있지만 최초의 생성자는 로그인 되어있지 않으면 따로 아래낼수 없다. 그렇기 때문에 config -> JapConfig -> publick AuditorWare를 사용
//    최초에 insert할때 ㅎ나번 넣는데
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createAt;
//    최초 insert할때 한번 넣는다
    @CreatedBy
    @Column(nullable = false)
    private String createBy;
//    작성당시 매번 넣어준다
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;
//    작성 당시 매번 넣어준다
    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedBy;

    protected Ex01_1_Article_엔티티로등록(){

    }




//    사용자가 입력하는 값만 받는 생성자 -> 나머지는 메터 시스템이  알아서
    private Ex01_1_Article_엔티티로등록(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }



    public static Ex01_1_Article_엔티티로등록 of(String title, String content, String hashtag){
        return new Ex01_1_Article_엔티티로등록(title,content,hashtag);
    }

//    동등성 비교
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ex01_1_Article_엔티티로등록 that = (Ex01_1_Article_엔티티로등록) o;
        return Objects.equals(id, that.id);
    }

//    동일성 비교
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * equals - 값이 같으면 true / 둘다 null 이어도 true
     *
     * hashCode - 객체를 식별하는 Integer값
     * 객체의 값을 특정 알고리즘을 이용 계싼된 정수값을 지징
     * hashcode 사용하는 이유는 객체 비교시 비용이 낮다
     * 자바에서 2개의 객체가 같은지 비교할때 equals(를 쓰는데
     * 여러개의 객체 비교시 equals는 자원과 비용이 많다
     */
}

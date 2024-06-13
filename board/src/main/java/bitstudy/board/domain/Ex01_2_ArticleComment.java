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
import java.util.Objects;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy")
})
public class Ex01_2_ArticleComment {
    @Id
    private Long id;

//   연관관계를 위해 매핑한다.
//    연관관계 -> Article과의 관계 표시, 단뱡항
    @Setter
    @ManyToOne(optional = false)
    private Ex01_1_Article_엔티티로등록 article;


    @Column(nullable = false, length = 500)
    @Setter
    private String content;


//    메타데이터
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

    protected Ex01_2_ArticleComment(){

    }

    private Ex01_2_ArticleComment(Ex01_1_Article_엔티티로등록 article, String content){
        this.article = article;
        this.content = content;

    }

    public static Ex01_2_ArticleComment of(Ex01_1_Article_엔티티로등록 article, String content){
        return new Ex01_2_ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ex01_2_ArticleComment that = (Ex01_2_ArticleComment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    /**
        Ex01_1 Ex01_2 Ex01_3 다 하고 어플리케이션 실행(BoardApplication.java 실행
        서비스 탭에서도 실행 가능
        서비스 탬 -> + 버튼 -> Run configration -> 스크롤 아래에 spring boot 클릭
     */
}

package bitstudy.board.repository;

import bitstudy.board.config.Ex01_3_JpaConfig;
import bitstudy.board.domain.Ex01_1_Article_엔티티로등록;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//슬라이스 테스트를 할수 있게 해주는 어노테이션 - 이 경우 수동으로 만든 JpaConfig 파일은 읽지 않기 때문에 아래의 @Import 어노테이션를 이용 해당 파일 정보를 읽어온다.
@DataJpaTest
@Import(Ex01_3_JpaConfig.class)
class Ex01_6_JpaRepoTest {

    Ex01_4_ArticleRepo articleRepo;
    Ex01_5_ArticleCommentRepo commentRepo;

    public Ex01_6_JpaRepoTest(@Autowired Ex01_4_ArticleRepo articleRepo, @Autowired Ex01_5_ArticleCommentRepo articleCommentRepo){
        this.articleRepo = articleRepo;
        this.commentRepo = articleCommentRepo;
    }

    @Test
    void selectTest(){
        List<Ex01_1_Article_엔티티로등록> article =  articleRepo.findAll();
        System.out.println("가져온 숫자 :: "+article.size());
        assertThat(article).isNotNull().hasSize(1000);
    }


}
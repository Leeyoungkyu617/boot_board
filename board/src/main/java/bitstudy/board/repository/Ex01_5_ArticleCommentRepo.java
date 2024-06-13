package bitstudy.board.repository;

import bitstudy.board.domain.Ex01_1_Article_엔티티로등록;
import bitstudy.board.domain.Ex01_2_ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

// TDD에 사용할 임시 파일(이거 이용 DB접근)
public interface Ex01_5_ArticleCommentRepo extends JpaRepository<Ex01_2_ArticleComment, Long> {
}

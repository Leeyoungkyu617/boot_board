package bitstudy.board.domain;

import java.time.LocalDateTime;

public class Ex00_2_ArticleComment {
    private Long id;

//   연관관계를 위해 매핑한다.
    private Ex00_1_Article article;
    private String content;


    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}

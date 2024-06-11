package bitstudy.board.domain;

import java.time.LocalDateTime;

public class ArticleComment  {
    private Long id;

//   연관관계를 위해 매핑한다.
    private Article article;
    private String content;


    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}

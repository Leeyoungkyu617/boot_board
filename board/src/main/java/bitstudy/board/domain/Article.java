package bitstudy.board.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String content;
    private String hashtage;

    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

}

package redwine.me.jpabbs.bbs;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Entity
public class Bbs {
    @Id @GeneratedValue
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created;

    @Column(updatable = false)
    private LocalDateTime updated;

    protected Bbs() {}

    @Builder
    public Bbs(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //수정 더티체킹 메서드
    public void modifyBbs(BbsRequest bbsRequest) {
        this.title = bbsRequest.getTitle();
        this.content = bbsRequest.getContent();
        this.updated = LocalDateTime.now();
    }
}

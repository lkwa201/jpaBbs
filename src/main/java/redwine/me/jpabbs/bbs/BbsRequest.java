package redwine.me.jpabbs.bbs;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BbsRequest {

    private String title;
    private String content;
    private String author;

    @Builder
    public BbsRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Bbs toEntity() {
        return Bbs.builder().
                title(title).
                content(content).
                author(author).
                build();
    }

}

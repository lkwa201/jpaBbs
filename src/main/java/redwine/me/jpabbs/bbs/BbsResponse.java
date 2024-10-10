package redwine.me.jpabbs.bbs;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BbsResponse {

    private String title;
    private String content;
    private String author;

    @Builder
    public BbsResponse(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static BbsResponse of(Bbs bbs) {
        return BbsResponse.builder().
                title(bbs.getTitle()).
                content(bbs.getContent()).
                author(bbs.getAuthor()).
                build();
    }

    public static List<BbsResponse> listOf(List<Bbs> bbs) {
        return bbs.stream().map(BbsResponse::of).collect(Collectors.toList());
    }

}

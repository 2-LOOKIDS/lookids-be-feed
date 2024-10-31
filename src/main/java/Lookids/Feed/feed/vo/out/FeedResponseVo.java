package Lookids.Feed.feed.vo.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseVo {

    private Long feedId;
    private String feedCode;
    private String content;
    private LocalDateTime createdAt;
    private String tag;

}

package Lookids.Feed.feed.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import Lookids.Feed.feed.dto.out.MediaUrlResponse;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseVo {

    private Long feedId;
    private String content;
    private List<MediaUrlResponse> mediaUrls;
    private Long authorId;
    private String authorImage;
    private LocalDateTime createdAt;
    private String tag;

}

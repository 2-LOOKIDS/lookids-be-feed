package lookids.feed.feed.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.feed.feed.dto.out.MediaUrlResponse;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseVo {

    private Long authorId;
    private String authorImage;
    private Long feedId;
    private String petCode;
    private LocalDateTime createdAt;
    private String tag;
    private String content;
    private List<MediaUrlResponse> mediaUrls;


}

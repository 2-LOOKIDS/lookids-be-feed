package lookids.feed.feed.vo.out;

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

    private String userUuid;
    private String petCode;
    private String content;
    private String contentMedia;
    private String tag;
    private LocalDateTime createAt;
}

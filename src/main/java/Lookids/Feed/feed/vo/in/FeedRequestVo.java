package lookids.feed.feed.vo.in;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedRequestVo {

    private String userUuid;
    private String petCode;
    private String feedCode;
    private Long authorId;
    private String authorImage;
    private String content;
    private List<String> mediaUrls;
    private String tag;
}

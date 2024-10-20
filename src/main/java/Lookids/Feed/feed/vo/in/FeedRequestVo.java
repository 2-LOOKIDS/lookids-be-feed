package lookids.feed.feed.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedRequestVo {

    private String userUuid;
    private String petCode;
    private String feedCode;
    private String content;
    private String contentMedia;
    private String tag;
}

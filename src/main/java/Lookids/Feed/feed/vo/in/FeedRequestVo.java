package Lookids.Feed.feed.vo.in;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedRequestVo {

    private String petCode;
    private String content;
    private List<String> tags;
    private List<String> mediaCode;

}

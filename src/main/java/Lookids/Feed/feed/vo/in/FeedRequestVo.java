package Lookids.Feed.feed.vo.in;

import java.util.List;

import Lookids.Feed.media.dto.in.MediaRequestDto;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedRequestVo {

    private String userUuid;
    private String petCode;
    private String content;
    private String tag;
    private List<MediaRequestDto> media;
}

package Lookids.Feed.feed.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import Lookids.Feed.media.dto.out.MediaResponseDto;
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
    private List<MediaResponseDto> mediaList;

}

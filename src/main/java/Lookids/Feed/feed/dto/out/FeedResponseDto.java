package Lookids.Feed.feed.dto.out;

import java.time.LocalDateTime;
import java.util.List;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.out.FeedResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponseDto {

    // private String feedCode;
    private String content;
    private LocalDateTime createdAt;
    private List<String> tags;
    // private List<MediaResponseDto> mediaList;

    public static FeedResponseDto toDto(Feed feed) {
        return FeedResponseDto.builder()
                // .feedCode(feed.getFeedCode())
                .createdAt(feed.getCreatedAt())
                .tags(feed.getTags())
                .content(feed.getContent())
                .build();
    }

    public FeedResponseVo toVo() {
        return FeedResponseVo.builder()
                // .feedCode(feedCode)
                .createdAt(createdAt)
                .tags(tags)
                .content(content)
                .build();
    }
}

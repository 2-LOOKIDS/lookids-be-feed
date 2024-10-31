package Lookids.Feed.feed.dto.out;

import java.time.LocalDateTime;

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

    private Long feedId;
    private String feedCode;
    private String content;
    private LocalDateTime createdAt;
    private String tag;

    public static FeedResponseDto toDto(Feed feed) {
        return FeedResponseDto.builder()
            .feedId(feed.getId())
            .feedCode(feed.getFeedCode())
            .createdAt(feed.getCreatedAt())
            .tag(feed.getTag())
            .content(feed.getContent())
            .build();
    }

    public FeedResponseVo toVo() {
        return FeedResponseVo.builder()
            .feedId(feedId)
            .feedCode(feedCode)
            .createdAt(createdAt)
            .tag(tag)
            .content(content)
            .build();
    }
}

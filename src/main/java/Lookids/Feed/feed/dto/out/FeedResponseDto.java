package Lookids.Feed.feed.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.out.FeedResponseVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponseDto {

    private Long feedId;
    private String feedCode;
    private Long authorId;
    private String authorImage;
    private String content;
    private List<MediaUrlResponse> mediaUrls;
    private LocalDateTime createdAt;
    private String tag;

    public static FeedResponseDto toDto(Feed feed) {
        return FeedResponseDto.builder()
            .feedId(feed.getId())
            .feedCode(feed.getFeedCode())
            .authorId(feed.getAuthorId())
            .authorImage(feed.getAuthorImage())
            .createdAt(feed.getCreatedAt())
            .tag(feed.getTag())
            .content(feed.getContent())
            .mediaUrls(feed.getMediaUrls().stream()
                .map(MediaUrlResponse::new)
                .collect(Collectors.toList()))
            .build();
    }

    public FeedResponseVo toVo() {
        return FeedResponseVo.builder()
            .feedId(feedId)
            .feedCode(feedCode)
            .authorId(authorId)
            .authorImage(authorImage)
            .createdAt(createdAt)
            .tag(tag)
            .content(content)
            .mediaUrls(mediaUrls)
            .build();
    }
}

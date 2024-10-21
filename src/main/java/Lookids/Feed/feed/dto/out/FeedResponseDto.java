package lookids.feed.feed.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.feed.feed.domain.Feed;
import lookids.feed.feed.vo.out.FeedResponseVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponseDto {

    private Long authorId;
    private String authorImage;
    private Long feedId;
    private String petCode;
    private LocalDateTime createdAt;
    private String tag;
    private String content;
    private List<MediaUrlResponse> mediaUrls;

    public static FeedResponseDto toDto(Feed feed) {
        return FeedResponseDto.builder()
                .authorId(feed.getAuthorId())
                .authorImage(feed.getAuthorImage())
                .feedId(feed.getId())
                .petCode(feed.getPetCode())
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
                .authorId(authorId)
                .authorImage(authorImage)
                .feedId(feedId)
                .petCode(petCode)
                .createdAt(createdAt)
                .tag(tag)
                .content(content)
                .mediaUrls(mediaUrls)
                .build();
    }
}

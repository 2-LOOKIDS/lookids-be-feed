package lookids.feed.feed.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.feed.feed.domain.Feed;
import lookids.feed.feed.vo.out.FeedResponseVo;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponseDto {

    private String userUuid;
    private String petCode;
    private String content;
    private String contentMedia;
    private String tag;
    private LocalDateTime createAt;

    public static FeedResponseDto toDto(Feed feed) {
        return FeedResponseDto.builder()
                .userUuid(feed.getUserUuid())
                .petCode(feed.getPetCode())
                .content(feed.getContent())
                .contentMedia(feed.getContentMedia())
                .tag(feed.getTag())
                .build();
    }

    public FeedResponseVo toVo() {
        return FeedResponseVo.builder()
                .userUuid(userUuid)
                .petCode(petCode)
                .content(content)
                .contentMedia(contentMedia)
                .tag(tag)
                .build();
    }
}

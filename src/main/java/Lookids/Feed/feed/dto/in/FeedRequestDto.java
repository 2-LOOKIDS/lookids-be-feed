package lookids.feed.feed.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.feed.feed.domain.Feed;
import lookids.feed.feed.vo.in.FeedRequestVo;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDto {

    private String userUuid;
    private String petCode;
    private String feedCode;
    private Long authorId;
    private String authorImage;
    private String content;
    private List<String> mediaUrls;
    private String tag;

    public Feed toEntity() {
        return Feed.builder()
                .userUuid(userUuid)
                .petCode(petCode)
                .feedCode(feedCode)
                .authorId(authorId)
                .authorImage(authorImage)
                .mediaUrls(mediaUrls)
                .content(content)
                .tag(tag)
                .build();
    }

    public static FeedRequestDto toDto(FeedRequestVo feedRequestVo) {
        return FeedRequestDto.builder()
                .userUuid(feedRequestVo.getUserUuid())
                .petCode(feedRequestVo.getPetCode())
                .feedCode(feedRequestVo.getFeedCode())
                .authorId(feedRequestVo.getAuthorId())
                .authorImage(feedRequestVo.getAuthorImage())
                .content(feedRequestVo.getContent())
                .mediaUrls(feedRequestVo.getMediaUrls())
                .tag(feedRequestVo.getTag())
                .build();
    }

}

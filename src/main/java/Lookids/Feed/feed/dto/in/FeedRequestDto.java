package lookids.feed.feed.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.feed.feed.domain.Feed;
import lookids.feed.feed.vo.in.FeedRequestVo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDto {

    private String userUuid;
    private String petCode;
    private String feedCode;
    private String content;
    private String contentMedia;
    private String tag;

    public Feed toEntity() {
        return Feed.builder()
                .userUuid(userUuid)
                .petCode(petCode)
                .feedCode(feedCode)
                .content(content)
                .contentMedia(contentMedia)
                .tag(tag)
                .build();
    }

    public static FeedRequestDto toDto(FeedRequestVo feedRequestVo) {
        return FeedRequestDto.builder()
                .userUuid(feedRequestVo.getUserUuid())
                .petCode(feedRequestVo.getPetCode())
                .feedCode(feedRequestVo.getFeedCode())
                .content(feedRequestVo.getContent())
                .contentMedia(feedRequestVo.getContentMedia())
                .tag(feedRequestVo.getTag())
                .build();
    }

}

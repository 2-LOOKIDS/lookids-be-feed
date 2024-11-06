package Lookids.Feed.feed.dto.in;

import java.util.List;
import java.util.stream.Collectors;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.in.FeedRequestVo;
import Lookids.Feed.media.domain.Media;
import Lookids.Feed.media.dto.in.MediaRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDto {

    private String userUuid;
    private String petCode;
    private String content;
    private String tag;
    private List<MediaRequestDto> media;


    public Feed toEntity(String feedCode) {
        return Feed.builder()
                .userUuid(userUuid)
                .petCode(petCode)
                .feedCode(feedCode)
                .content(content)
                .tag(tag)
                .build();
    }

    public List<Media> toMediaEntity(String feedCode, Feed feed) {
        return media.stream()
            .map(mediaDto -> Media.builder()
                .feedCode(feedCode)
                .mediaType(mediaDto.getMediaType())
                .mediaUrl(mediaDto.getMediaUrl())
                .mediaOrder(mediaDto.getMediaOrder())
                .latitude(mediaDto.getLatitude())
                .longitude(mediaDto.getLongitude())
                .feed(feed)
                .build())
            .collect(Collectors.toList());
    }

    public static FeedRequestDto toDto(FeedRequestVo feedRequestVo) {
        return FeedRequestDto.builder()
                .userUuid(feedRequestVo.getUserUuid())
                .petCode(feedRequestVo.getPetCode())
                .content(feedRequestVo.getContent())
                .tag(feedRequestVo.getTag())
                .media(feedRequestVo.getMedia())
                .build();
    }

}

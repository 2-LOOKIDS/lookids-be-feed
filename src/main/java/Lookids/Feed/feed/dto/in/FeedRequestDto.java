package Lookids.Feed.feed.dto.in;

import java.util.List;

import org.bson.types.ObjectId;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.domain.GPSInfo;
import Lookids.Feed.feed.vo.in.FeedRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FeedRequestDto {

    private ObjectId id;
    // private String feedCode;
    private String userUuid;
    private String petCode;
    private String content;
    private List<String> tags;
    private boolean isDeleted;
    private GPSInfo gpsInfo;
//    private List<MediaRequestDto> media;

    public Feed toEntity() {
       // String feedCode = UUID.randomUUID().toString();
        return Feed.builder()
            // .feedCode(feedCode)
            .userUuid(userUuid)
            .petCode(petCode)
            .content(content)
            .tags(tags)
            .gpSinfo(gpsInfo)
            .isDeleted(false)
            .build();
    }

    public static FeedRequestDto toDto(FeedRequestVo feedRequestVo, String userUuid) {
        return FeedRequestDto.builder()
            .userUuid(userUuid)
            .petCode(feedRequestVo.getPetCode())
            .content(feedRequestVo.getContent())
            .tags(feedRequestVo.getTags())
            .gpsInfo(feedRequestVo.getGpsInfo())
//          .media(feedRequestVo.getMedia())
            .build();
    }

    public static FeedRequestDto toDelete(Feed feed) {
        return FeedRequestDto.builder()
            .id(feed.getId())
            .userUuid(feed.getUserUuid())
            .petCode(feed.getPetCode())
            .isDeleted(true)
            .content(feed.getContent())
            .tags(feed.getTags())
            .gpsInfo(feed.getGpSinfo())
            .build();
    }

    public Feed toEntityForUpdate() {
        return Feed.builder()
            .id(id)
            .userUuid(userUuid)
            .petCode(petCode)
            .content(content)
            .tags(tags)
            .isDeleted(isDeleted)
            .gpSinfo(gpsInfo)
            .build();
    }
}

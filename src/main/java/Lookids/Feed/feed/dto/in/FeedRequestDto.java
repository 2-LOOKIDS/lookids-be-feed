package Lookids.Feed.feed.dto.in;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.in.FeedRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FeedRequestDto {

    private ObjectId id;
    private String feedCode;
    private String uuid;
    private String petCode;
    private String content;
    private List<String> tags;
    private boolean state;
    private List<String> mediaUrl;
    private LocalDateTime createdAt;


    public Feed toEntity() {
        return Feed.builder()
            .feedCode(UUID.randomUUID().toString())
            .uuid(uuid)
            .petCode(petCode)
            .content(content)
            .tags(tags)
            .mediaUrl(mediaUrl)
            .state(false)
            .createdAt(createdAt)
            .build();
    }

    public static FeedRequestDto toDto(FeedRequestVo feedRequestVo, String uuid) {
        return FeedRequestDto.builder()
            .uuid(uuid)
            .petCode(feedRequestVo.getPetCode())
            .content(feedRequestVo.getContent())
            .tags(feedRequestVo.getTags())
            .mediaUrl(feedRequestVo.getMediaUrl())
            .build();
    }

    public static FeedRequestDto toDelete(Feed feed) {
        return FeedRequestDto.builder()
            .id(feed.getId())
            .uuid(feed.getUuid())
            .feedCode(feed.getFeedCode())
            .petCode(feed.getPetCode())
            .state(true)
            .content(feed.getContent())
            .tags(feed.getTags())
            .mediaUrl(feed.getMediaUrl())
            .createdAt(feed.getCreatedAt())
            .build();
    }

    public Feed toEntityForUpdate() {
        return Feed.builder()
            .id(id)
            .feedCode(feedCode)
            .uuid(uuid)
            .petCode(petCode)
            .content(content)
            .tags(tags)
            .mediaUrl(mediaUrl)
            .state(state)
            .createdAt(createdAt)
            .build();
    }

    public FeedKafkaDto toDto(Feed savefeed) {
        return FeedKafkaDto.builder()
            .feedCode(savefeed.getFeedCode())
            .uuid(uuid)
            .petCode(petCode)
            .content(content)
            .tags(tags)
            .state(state)
            .mediaUrl(mediaUrl)
            .createdAt(savefeed.getCreatedAt())
            .build();
    }
}

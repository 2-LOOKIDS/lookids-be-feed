package Lookids.Feed.feed.dto.in;

import java.time.LocalDateTime;
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
    private List<String> mediaCode;
    private LocalDateTime createdAt;


    public Feed toEntity() {
        return Feed.builder()
            .feedCode(UUID.randomUUID().toString())
            .uuid(uuid)
            .petCode(petCode)
            .content(content)
            .tags(tags)
            .mediaCode(mediaCode)
            .state(false)
            .build();
    }

    public static FeedRequestDto toDto(FeedRequestVo feedRequestVo, String uuid) {
        return FeedRequestDto.builder()
            .uuid(uuid)
            .petCode(feedRequestVo.getPetCode())
            .content(feedRequestVo.getContent())
            .tags(feedRequestVo.getTags())
            .mediaCode(feedRequestVo.getMediaCode())
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
            .mediaCode(feed.getMediaCode())
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
            .mediaCode(mediaCode)
            .state(state)
            // .createdAt(createdAt)
            .build();
    }

    public KafkaDto toDto(Feed savefeed) {
        return KafkaDto.builder()
            .feedCode(savefeed.getFeedCode())
            .uuid(this.uuid)
            .petCode(this.petCode)
            .content(this.content)
            .tags(this.tags)
            .state(this.state)
            .mediaCode(this.mediaCode)
            .createdAt(savefeed.getCreatedAt())
            .build();
    }
}

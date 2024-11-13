package Lookids.Feed.feed.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Document
@NoArgsConstructor
public class Feed {

    @Id
    private ObjectId id;
    private String feedCode;
    private String uuid;
    private String content;
    private boolean state;
    private String petCode;
    private List<String> tags;
    private List<String> mediaCode;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Feed(
        ObjectId id,
        String uuid,
        String feedCode,
        String content,
        String petCode,
        List<String> tags,
        boolean state,
        LocalDateTime createdAt,
        List<String> mediaCode
    ) {
        this.id = id;
        this.feedCode = feedCode;
        this.uuid = uuid;
        this.content = content;
        this.petCode = petCode;
        this.tags = tags;
        this.state = state;
        this.createdAt = createdAt;
        this.mediaCode = mediaCode;
    }
}

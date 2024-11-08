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
    private String userUuid;
    // private String feedCode;
    private String content;
    private boolean isDeleted;
    private String petCode;
    private List<String> tags;
    private GPSInfo gpSinfo;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Feed(
        ObjectId id,
        String userUuid,
        // String feedCode,
        String content,
        String petCode,
        List<String> tags,
        boolean isDeleted,
        LocalDateTime createdAt,
        GPSInfo gpSinfo
    ) {
        this.id = id;
        // this.feedCode = feedCode;
        this.userUuid = userUuid;
        this.content = content;
        this.petCode = petCode;
        this.tags = tags;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.gpSinfo = gpSinfo;
    }
}

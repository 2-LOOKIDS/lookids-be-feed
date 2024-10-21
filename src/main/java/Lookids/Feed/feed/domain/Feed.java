package lookids.feed.feed.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.feed.common.entity.BaseEntity;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userUuid;

    @Column(nullable = false)
    private Long authorId;

    @Column(nullable = false)
    private String authorImage;

    @Column(nullable = false)
    private String feedCode;

    @Column(nullable = false)
    private String petCode;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private List<String> mediaUrls;

    @Column(nullable = false)
    private String tag;
}

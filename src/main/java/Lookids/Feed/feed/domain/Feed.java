package lookids.feed.feed.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String userUuid;

    @Column(nullable = false, length = 100)
    private String feedCode;

    @Column(nullable = false, length = 100)
    private String petCode;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(length = 100)
    private String contentMedia;

    @Column(nullable = false, length = 100)
    private String tag;

    @Column(nullable = false, length = 100)
    private LocalDateTime createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = (this.createAt == null) ? LocalDateTime.now() : this.createAt;
    }

}

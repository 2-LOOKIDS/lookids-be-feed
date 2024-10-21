package lookids.feed.feed.infrastructure;

import lookids.feed.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findByFeedCode(String feedCode);
     List<Feed> findByUserUuid(String userUuid);
    void deleteByFeedCode(String feedCode);
}

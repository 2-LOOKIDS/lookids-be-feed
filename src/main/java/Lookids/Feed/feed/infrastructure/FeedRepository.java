package Lookids.Feed.feed.infrastructure;

import Lookids.Feed.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findByFeedCode(String feedCode);
    void deleteByFeedCode(String feedCode);
}

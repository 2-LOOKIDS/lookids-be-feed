package Lookids.Feed.feed.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Lookids.Feed.feed.domain.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findByFeedCode(String feedCode);
    Optional<Feed> findByFeedCodeAndIsDeletedFalse(String feedCode);
}

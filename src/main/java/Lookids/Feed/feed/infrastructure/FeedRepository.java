package Lookids.Feed.feed.infrastructure;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import Lookids.Feed.feed.domain.Feed;

public interface FeedRepository extends MongoRepository<Feed, ObjectId> {
    // Optional<Feed> findByFeedCode(String feedCode);
    // Optional<Feed> findByFeedCodeAndIsDeletedFalse(String feedCode);
	Optional<Feed> findByFeedCodeAndStateFalse(String feedCode);
}

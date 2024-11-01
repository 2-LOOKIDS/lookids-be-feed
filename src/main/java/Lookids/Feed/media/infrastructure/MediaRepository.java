package Lookids.Feed.media.infrastructure;

import Lookids.Feed.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import Lookids.Feed.media.domain.Media;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByFeedCode(String feedCode);

}

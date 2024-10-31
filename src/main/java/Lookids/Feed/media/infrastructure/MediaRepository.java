package Lookids.Feed.media.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import Lookids.Feed.media.domain.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {


}

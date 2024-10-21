package lookids.feed.feed.application;

import lookids.feed.feed.dto.in.FeedRequestDto;
import lookids.feed.feed.dto.out.FeedResponseDto;

import java.util.List;

public interface FeedService {

    void createFeed(FeedRequestDto feedRequestDto);
    List<FeedResponseDto> readUserFeedList(String userUuid);
    FeedResponseDto readFeed(String feedCode);
    void deleteFeed(String feedCode);
}

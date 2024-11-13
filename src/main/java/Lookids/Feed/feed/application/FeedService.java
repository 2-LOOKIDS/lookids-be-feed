package Lookids.Feed.feed.application;

import Lookids.Feed.feed.dto.in.FeedRequestDto;

public interface FeedService {

    void createFeed(FeedRequestDto feedRequestDto);
    // CursorPage<FeedResponseVo> readUserFeedList(String userUuid, Integer page, Integer lastId);
    // FeedResponseDto readFeed(String feedCode);
    // FeedDetailResponseDto readFeedDetail(String feedCode);
    void deleteFeed(String uuid, String feedCode);
}

package Lookids.Feed.feed.application;

import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.dto.out.FeedDetailResponseDto;
import Lookids.Feed.feed.dto.out.FeedResponseDto;
import Lookids.Feed.feed.vo.out.FeedResponseVo;



public interface FeedService {

    void createFeed(FeedRequestDto feedRequestDto);
    CursorPage<FeedResponseVo> readUserFeedList(String userUuid, Integer page, Integer lastId);
    FeedResponseDto readFeed(String feedCode);
    FeedDetailResponseDto readFeedDetail(String feedCode);
    void deleteFeed(String feedCode);
}

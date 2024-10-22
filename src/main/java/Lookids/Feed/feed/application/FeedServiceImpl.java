package Lookids.Feed.feed.application;

import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.dto.out.FeedDetailResponseDto;
import Lookids.Feed.feed.infrastructure.FeedRepositoryCustom;
import Lookids.Feed.feed.vo.out.FeedResponseVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.common.exception.BaseException;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.dto.out.FeedResponseDto;
import Lookids.Feed.feed.infrastructure.FeedRepository;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final FeedRepositoryCustom feedRepositoryCustom;

    @Override
    public void createFeed(FeedRequestDto feedRequestDto) {
        feedRepository.save(feedRequestDto.toEntity());
    }

     @Override
     public CursorPage<FeedResponseVo> readUserFeedList(String userUuid, Integer page, Integer lastId) {
        return feedRepositoryCustom.readUserFeedList(userUuid, page, lastId);
     }

    @Override
    public FeedResponseDto readFeed(String feedCode) {
        return FeedResponseDto.toDto(feedRepository.findByFeedCode(feedCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED)));
    }

    @Override
    public FeedDetailResponseDto readFeedDetail(String feedCode) {
        return FeedDetailResponseDto.toDto(feedRepository.findByFeedCode(feedCode)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED)));
    }

    @Transactional
    @Override
    public void deleteFeed(String feedCode) {
        Feed feed = feedRepository.findByFeedCode(feedCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
        feedRepository.deleteByFeedCode(feedCode);
    }
}

package lookids.feed.feed.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lookids.feed.common.entity.BaseResponseStatus;
import lookids.feed.common.exception.BaseException;
import lookids.feed.feed.domain.Feed;
import lookids.feed.feed.dto.in.FeedRequestDto;
import lookids.feed.feed.dto.out.FeedResponseDto;
import lookids.feed.feed.infrastructure.FeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    @Override
    public void createFeed(FeedRequestDto feedRequestDto) {
        feedRepository.save(feedRequestDto.toEntity());
    }

    @Override
    public List<FeedResponseDto> readFeedList() {
        List<Feed> feedList = feedRepository.findAll();
        return feedList.stream()
                .map(FeedResponseDto::toDto).toList();
    }

    @Override
    public FeedResponseDto readFeed(String feedCode) {
        return FeedResponseDto.toDto(feedRepository.findByFeedCode(feedCode)
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

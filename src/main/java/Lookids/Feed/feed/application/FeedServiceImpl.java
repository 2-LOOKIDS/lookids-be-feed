package Lookids.Feed.feed.application;

import org.bson.types.ObjectId;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.common.exception.BaseException;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.infrastructure.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final KafkaTemplate<String, FeedRequestDto> kafkaTemplate;
    private static final String TOPIC = "feed-events";


    @Override
    public void createFeed(FeedRequestDto feedRequestDto){
        feedRepository.save(feedRequestDto.toEntity());
        // ObjectMapper objectMapper = new ObjectMapper();
        // kafkaTemplate.send(TOPIC, feedRequestDto);
    }

    //  @Override
    //  public CursorPage<FeedResponseVo> readUserFeedList(String userUuid, Integer page, Integer lastId) {
    //     return feedRepositoryCustom.findByUserUuidAndIsDeletedFalse(userUuid, page, lastId);
    //  }
    //
    // @Override
    // public FeedResponseDto readFeed(String feedCode) {
    //     Feed feed = feedRepository.findByFeedCodeAndIsDeletedFalse(feedCode)
    //             .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
    //     List<MediaResponseDto> mediaList = mediaRepository.findByFeedCode(feedCode).stream()
    //             .map(MediaResponseDto::toDto)
    //             .collect(Collectors.toList());
    //     return FeedResponseDto.toDto(feed, mediaList);
    // }
    //
    // @Override
    // public FeedDetailResponseDto readFeedDetail(String feedCode) {
    //     Feed feed = feedRepository.findByFeedCodeAndIsDeletedFalse(feedCode)
    //             .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
    //     List<MediaResponseDto> mediaList = mediaRepository.findByFeedCode(feedCode).stream()
    //             .map(MediaResponseDto::toDto)
    //             .collect(Collectors.toList());
    //     return FeedDetailResponseDto.toDto(feed, mediaList);
    // }

    @Override
    public void deleteFeed(String userUuid, ObjectId id) {
        Feed feed = feedRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
        feedRepository.save(FeedRequestDto.toDelete(feed).toEntityForUpdate());
    }
}

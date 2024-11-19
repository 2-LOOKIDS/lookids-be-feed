package Lookids.Feed.feed.application;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.common.exception.BaseException;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.dto.in.FeedKafkaDto;
import Lookids.Feed.feed.dto.in.UserKafkaDto;
import Lookids.Feed.feed.infrastructure.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final KafkaTemplate<String, FeedKafkaDto> feedkafkaTemplate;
    private final KafkaTemplate<String, UserKafkaDto> userkafkaTemplate;

    @Override
    public void createFeed(FeedRequestDto feedRequestDto){
        Feed savefeed = feedRepository.save(feedRequestDto.toEntity());
        log.info("save: {} ", savefeed);
        UserKafkaDto userKafkaDto = UserKafkaDto.builder()
            .uuid(feedRequestDto.getUuid()).build();
        CompletableFuture<SendResult<String, UserKafkaDto>> future = userkafkaTemplate.send("userprofile-request", userKafkaDto);
        log.info("UUID Kafka: {}",userKafkaDto);
        FeedKafkaDto feedKafkaDto = feedRequestDto.toDto(savefeed);
        CompletableFuture<SendResult<String, FeedKafkaDto>> future1 = feedkafkaTemplate.send("feed-create", feedKafkaDto);
        log.info("Sent feed request DTO to Kafka: {}", feedKafkaDto);
    }

    @Override
    public void deleteFeed(String uuid, String feedCode) {
        Feed feed = feedRepository.findByFeedCodeAndStateFalse(feedCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
        feedRepository.save(FeedRequestDto.toDelete(feed).toEntityForUpdate());
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
}

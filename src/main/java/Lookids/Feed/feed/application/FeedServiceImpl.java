package Lookids.Feed.feed.application;

import org.apache.catalina.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.common.exception.BaseException;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.dto.in.KafkaDto;
import Lookids.Feed.feed.dto.in.UserKafkaDto;
import Lookids.Feed.feed.infrastructure.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final KafkaTemplate<String, KafkaDto> kafkaTemplate;
    private final KafkaTemplate<String, UserKafkaDto> userkafkaTemplate;

    @Override
    public void createFeed(FeedRequestDto feedRequestDto){
        Feed savefeed = feedRepository.save(feedRequestDto.toEntity());
        UserKafkaDto userKafkaDto = UserKafkaDto.builder()
            .uuid(feedRequestDto.getUuid()).build();
        userkafkaTemplate.send("userprofile-request",userKafkaDto);
        // log.info("UUID Kafka: {}",userKafkaDto);
        KafkaDto kafkaDto = feedRequestDto.toDto(savefeed);
        kafkaTemplate.send("feed-create", kafkaDto);
        // log.info("Sent feed request DTO to Kafka: {}", kafkaDto);
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
    public void deleteFeed(String uuid, String feedCode) {
        Feed feed = feedRepository.findByFeedCodeAndStateFalse(feedCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
        feedRepository.save(FeedRequestDto.toDelete(feed).toEntityForUpdate());
    }
}

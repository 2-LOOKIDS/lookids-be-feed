package Lookids.Feed.feed.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import Lookids.Feed.media.dto.out.MediaResponseDto;
import org.springframework.stereotype.Service;

import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.common.exception.BaseException;
import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.dto.out.FeedDetailResponseDto;
import Lookids.Feed.feed.dto.out.FeedResponseDto;
import Lookids.Feed.feed.infrastructure.FeedRepository;
import Lookids.Feed.feed.infrastructure.FeedRepositoryCustom;
import Lookids.Feed.feed.vo.out.FeedResponseVo;
import Lookids.Feed.media.domain.Media;
import Lookids.Feed.media.infrastructure.MediaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final MediaRepository mediaRepository;
    private final FeedRepositoryCustom feedRepositoryCustom;

    @Override
    public void createFeed(FeedRequestDto feedRequestDto) {
        String feedCode;
        feedCode = UUID.randomUUID().toString();
        Feed saveFeed = feedRepository.save(feedRequestDto.toEntity(feedCode));
        List<Media> mediaList = feedRequestDto.toMediaEntity(feedCode, saveFeed);
        mediaRepository.saveAll(mediaList);
    }

     @Override
     public CursorPage<FeedResponseVo> readUserFeedList(String userUuid, Integer page, Integer lastId) {
        return feedRepositoryCustom.findByUserUuidAndIsDeletedFalse(userUuid, page, lastId);
     }

    @Override
    public FeedResponseDto readFeed(String feedCode) {
        Feed feed = feedRepository.findByFeedCodeAndIsDeletedFalse(feedCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
        List<MediaResponseDto> mediaList = mediaRepository.findByFeedCode(feedCode).stream()
                .map(MediaResponseDto::toDto)
                .collect(Collectors.toList());
        return FeedResponseDto.toDto(feed, mediaList);
    }

    @Override
    public FeedDetailResponseDto readFeedDetail(String feedCode) {
        Feed feed = feedRepository.findByFeedCodeAndIsDeletedFalse(feedCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
        List<MediaResponseDto> mediaList = mediaRepository.findByFeedCode(feedCode).stream()
                .map(MediaResponseDto::toDto)
                .collect(Collectors.toList());
        return FeedDetailResponseDto.toDto(feed, mediaList);
    }

    @Transactional
    @Override
    public void deleteFeed(String feedCode) {
        Feed feed = feedRepository.findByFeedCode(feedCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
        feed.setDeleted(true);
        feedRepository.save(feed);
    }
}

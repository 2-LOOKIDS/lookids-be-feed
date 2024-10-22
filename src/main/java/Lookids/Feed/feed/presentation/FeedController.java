package Lookids.Feed.feed.presentation;

import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.vo.out.FeedDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import Lookids.Feed.common.entity.BaseResponse;
import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.feed.application.FeedService;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.vo.in.FeedRequestVo;
import Lookids.Feed.feed.vo.out.FeedResponseVo;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {

    private final FeedService feedService;

    @Operation(summary = "feed 등록 API", description = "feed 등록 API 입니다.", tags = {"Feed"})
    @PostMapping
    public BaseResponse<Void> createFeed(@RequestBody FeedRequestVo feedRequestVo) {
        feedService.createFeed(FeedRequestDto.toDto(feedRequestVo));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

     @Operation(summary = "userfeed 조회 API", description = "userfeed 조회 API 입니다.", tags = {"Feed"})
     @GetMapping("/userfeed")
     public BaseResponse<CursorPage<FeedResponseVo>> readUserFeedList(
         @RequestParam String userUuid,
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(required = false) Integer lastId) {
        return new BaseResponse<>(feedService.readUserFeedList(userUuid, page, lastId));
    }

    @Operation(summary = "feed 조회 API", description = "feed 조회 API 입니다.", tags = {"Feed"})
    @GetMapping()
    public BaseResponse<FeedResponseVo> readFeed(@RequestParam String feedCode) {
        return new BaseResponse<>(feedService.readFeed(feedCode).toVo());
    }

    @Operation(summary = "feed detail 조회 API", description = "feed detail 조회 API 입니다.", tags = {"Feed"})
    @GetMapping("/detail")
    public BaseResponse<FeedDetailResponseVo> readFeedDetail(@RequestParam String feedCode) {
        return new BaseResponse<>(feedService.readFeedDetail(feedCode).toVo());
    }

    @Operation(summary = "feed 삭제 API", description = "feed 삭제 API 입니다.", tags = {"Feed"})
    @DeleteMapping
    public BaseResponse<Void> deleteFeed(@RequestParam String feedCode) {
        feedService.deleteFeed(feedCode);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}

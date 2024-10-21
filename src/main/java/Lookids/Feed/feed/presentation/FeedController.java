package lookids.feed.feed.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.feed.common.entity.BaseResponse;
import lookids.feed.common.entity.BaseResponseStatus;
import lookids.feed.feed.application.FeedService;
import lookids.feed.feed.dto.in.FeedRequestDto;
import lookids.feed.feed.dto.out.FeedResponseDto;
import lookids.feed.feed.vo.in.FeedRequestVo;
import lookids.feed.feed.vo.out.FeedResponseVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

     @Operation(summary = "userFeed 조회 API", description = "userFeed 조회 API 입니다.", tags = {"Feed"})
     @GetMapping("/userFeed")
     public BaseResponse<List<FeedResponseVo>> readUserFeedList(@RequestParam("user_uuid") String userUuid) {
         List<FeedResponseVo> feedList = feedService.readUserFeedList(userUuid)
                 .stream().map(FeedResponseDto::toVo)
                 .toList();
         return new BaseResponse<>(feedList);
     }

    @Operation(summary = "feed 조회 API", description = "feed 조회 API 입니다.", tags = {"Feed"})
    @GetMapping
    public BaseResponse<FeedResponseVo> readFeed(@RequestParam("feed_code") String feedCode) {
        return new BaseResponse<>(feedService.readFeed(feedCode).toVo());
    }

    @Operation(summary = "feed 삭제 API", description = "feed 삭제 API 입니다.", tags = {"Feed"})
    @DeleteMapping
    public BaseResponse<Void> deleteFeed(@RequestParam String feedCode) {
        feedService.deleteFeed(feedCode);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}

package Lookids.Feed.feed.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Lookids.Feed.common.entity.BaseResponse;
import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.application.FeedService;
import Lookids.Feed.feed.vo.out.FeedDetailResponseVo;
import Lookids.Feed.feed.vo.out.FeedResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feed/read")
public class FeedReadController {

	private final FeedService feedService;

	@Operation(summary = "user feed 조회 API", description = "user feed 조회 API 입니다.", tags = {"Feed"})
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
}


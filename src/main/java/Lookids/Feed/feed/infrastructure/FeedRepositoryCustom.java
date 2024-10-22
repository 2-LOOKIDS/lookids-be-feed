package Lookids.Feed.feed.infrastructure;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.dto.out.FeedResponseDto;
import Lookids.Feed.feed.vo.out.FeedResponseVo;

@Repository
public interface FeedRepositoryCustom {

	CursorPage<FeedResponseVo> readUserFeedList(String userUuid, Integer page, Integer lastId);
}

package Lookids.Feed.feed.infrastructure;

import org.springframework.stereotype.Repository;

import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.vo.out.FeedResponseVo;

@Repository
public interface FeedRepositoryCustom {

	CursorPage<FeedResponseVo> findByUserUuidAndIsDeletedFalse(String userUuid, Integer page, Integer lastId);
}

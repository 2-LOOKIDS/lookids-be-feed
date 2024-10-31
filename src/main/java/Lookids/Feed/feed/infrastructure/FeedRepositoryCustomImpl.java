package Lookids.Feed.feed.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.domain.QFeed;
import Lookids.Feed.feed.vo.out.FeedResponseVo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class FeedRepositoryCustomImpl implements FeedRepositoryCustom{

	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final int DEFAULT_PAGE_NUMBER = 0;

	private final JPAQueryFactory jpaQueryFactory;

	public CursorPage<FeedResponseVo> findByUserUuidAndIsDeletedFalse(String userUuid, Integer page, Integer lastId) {

		QFeed feed = QFeed.feed;
		BooleanBuilder builder = new BooleanBuilder();

		Optional.ofNullable(userUuid).ifPresent(uuid -> builder.and(feed.userUuid.eq(uuid)));
		builder.and(feed.isDeleted.eq(false));

		int currentPageSize = DEFAULT_PAGE_SIZE;
		int offset = 0;

		if (lastId != null) {
			builder.and(feed.id.lt(lastId));
		} else {
			int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
			offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;
		}

		List<Feed> content = jpaQueryFactory
			.selectFrom(feed)
			.where(builder)
			.orderBy(feed.createdAt.desc())
			.offset(offset)
			.limit(currentPageSize)
			.fetch();

		boolean hasNext = false;

		if (!content.isEmpty()) {
			hasNext = content.size() == currentPageSize;
		}

		List<FeedResponseVo> userfeedList = content.stream()
			.map(feedEntity -> FeedResponseVo.builder()
				.feedId(feedEntity.getId())
				.feedCode(feedEntity.getFeedCode())
				.content(feedEntity.getContent())
				.createdAt(feedEntity.getCreatedAt())
				.tag(feedEntity.getTag())
				.build())
			.toList();

		return new CursorPage<> (userfeedList, hasNext, currentPageSize, Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER));
	}
}

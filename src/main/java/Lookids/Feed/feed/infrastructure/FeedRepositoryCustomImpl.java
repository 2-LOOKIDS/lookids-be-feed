package Lookids.Feed.feed.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import Lookids.Feed.common.utills.CursorPage;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.domain.QFeed;
import Lookids.Feed.feed.dto.out.MediaUrlResponse;
import Lookids.Feed.feed.vo.out.FeedResponseVo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class FeedRepositoryCustomImpl implements FeedRepositoryCustom{

	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final int DEFAULT_PAGE_NUMBER = 0;

	private final JPAQueryFactory jpaQueryFactory;

	public CursorPage<FeedResponseVo> readUserFeedList(String userUuid, Integer page, Integer lastId) {

		QFeed feed = QFeed.feed;
		BooleanBuilder builder = new BooleanBuilder();

		Optional.ofNullable(userUuid).ifPresent(uuid -> builder.and(feed.userUuid.eq(uuid)));

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

		Long nextCursor = null;
		boolean hasNext = false;

		if (!content.isEmpty()) {
			hasNext = content.size() == currentPageSize;
			if (hasNext) {
				nextCursor = content.get(content.size() - 1).getId(); // 마지막 피드의 ID를 다음 커서로 설정
			}
		}

		List<FeedResponseVo> userfeedList = content.stream()
			.map(feedEntity -> FeedResponseVo.builder()
				.feedId(feedEntity.getId())
				.feedCode(feedEntity.getFeedCode())
				.content(feedEntity.getContent())
				.mediaUrls(feedEntity.getMediaUrls().stream()
					.map(mediaUrl -> MediaUrlResponse.builder()
						.mediaUrl(mediaUrl)
						.build())
					.collect(Collectors.toList()))
				.authorId(feedEntity.getAuthorId())
				.authorImage(feedEntity.getAuthorImage())
				.createdAt(feedEntity.getCreatedAt())
				.tag(feedEntity.getTag())
				.build())
			.toList();

		return new CursorPage<> (userfeedList, nextCursor, hasNext, currentPageSize, Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER));
	}
}

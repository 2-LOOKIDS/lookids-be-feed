package Lookids.Feed.feed.dto.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.out.FeedDetailResponseVo;
import Lookids.Feed.feed.vo.out.FeedResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDetailResponseDto {

	private Long authorId;
	private String authorImage;
	private String feedCode;
	private String petCode;
	private String content;
	private LocalDateTime createdAt;
	private String tag;
	private List<MediaUrlResponse> mediaUrls;

	public static FeedDetailResponseDto toDto(Feed feed) {
		return FeedDetailResponseDto.builder()
			.authorId(feed.getAuthorId())
			.authorImage(feed.getAuthorImage())
			.feedCode(feed.getFeedCode())
			.petCode(feed.getPetCode())
			.content(feed.getContent())
			.createdAt(feed.getCreatedAt())
			.tag(feed.getTag())
			.mediaUrls(feed.getMediaUrls().stream()
				.map(MediaUrlResponse::new)
				.collect(Collectors.toList()))
			.build();
	}

	public FeedDetailResponseVo toVo() {
		return FeedDetailResponseVo.builder()
			.authorId(authorId)
			.authorImage(authorImage)
			.feedCode(feedCode)
			.petCode(petCode)
			.createdAt(createdAt)
			.tag(tag)
			.content(content)
			.mediaUrls(mediaUrls)
			.build();
	}
}

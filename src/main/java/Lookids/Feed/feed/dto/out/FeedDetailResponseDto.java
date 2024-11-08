package Lookids.Feed.feed.dto.out;

import java.time.LocalDateTime;
import java.util.List;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.out.FeedDetailResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDetailResponseDto {

	// private String feedCode;
	private String petCode;
	private String content;
	private LocalDateTime createdAt;
	private List<String> tags;


	public static FeedDetailResponseDto toDto(Feed feed) {
		return FeedDetailResponseDto.builder()
				// .feedCode(feed.getFeedCode())
				.petCode(feed.getPetCode())
				.content(feed.getContent())
				.createdAt(feed.getCreatedAt())
				.tags(feed.getTags())
				.build();
	}

	public FeedDetailResponseVo toVo() {
		return FeedDetailResponseVo.builder()
				// .feedCode(feedCode)
				.petCode(petCode)
				.createdAt(createdAt)
				.tags(tags)
				.content(content)
				.build();
	}
}

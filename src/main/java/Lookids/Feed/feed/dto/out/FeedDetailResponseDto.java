package Lookids.Feed.feed.dto.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.vo.out.FeedDetailResponseVo;
import Lookids.Feed.media.dto.out.MediaResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDetailResponseDto {

	private String feedCode;
	private String petCode;
	private String content;
	private LocalDateTime createdAt;
	private String tag;
	private List<MediaResponseDto> mediaList;

	public static FeedDetailResponseDto toDto(Feed feed, List<MediaResponseDto> mediaList) {
		return FeedDetailResponseDto.builder()
				.feedCode(feed.getFeedCode())
				.petCode(feed.getPetCode())
				.content(feed.getContent())
				.createdAt(feed.getCreatedAt())
				.tag(feed.getTag())
				.mediaList(mediaList)
				.build();
	}

	public FeedDetailResponseVo toVo() {
		return FeedDetailResponseVo.builder()
				.feedCode(feedCode)
				.petCode(petCode)
				.createdAt(createdAt)
				.tag(tag)
				.content(content)
				.mediaList(mediaList)
				.build();
	}
}

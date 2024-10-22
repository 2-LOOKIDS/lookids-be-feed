package Lookids.Feed.feed.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import Lookids.Feed.feed.dto.out.MediaUrlResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedDetailResponseVo {

	private Long authorId;
	private String authorImage;
	private String feedCode;
	private String petCode;
	private String content;
	private LocalDateTime createdAt;
	private String tag;
	private List<MediaUrlResponse> mediaUrls;

}

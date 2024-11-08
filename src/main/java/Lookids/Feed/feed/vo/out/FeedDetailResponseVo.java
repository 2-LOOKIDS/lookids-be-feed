package Lookids.Feed.feed.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import Lookids.Feed.feed.domain.GPSInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedDetailResponseVo {

	// private String feedCode;
	private String petCode;
	private String content;
	private LocalDateTime createdAt;
	private List<String> tags;
	private GPSInfo gpsInfo;

}

package Lookids.Feed.feed.dto.in;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class KafkaDto {

	private String feedCode;
	private String uuid;
	private String petCode;
	private String content;
	private List<String> tags;
	private boolean state;
	private List<String> mediaCode;
	private LocalDateTime createdAt;

	@Builder
	public KafkaDto(String feedCode, String uuid, String petCode, String content,
		List<String> tags, boolean state, List<String> mediaCode, LocalDateTime createdAt) {
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.petCode = petCode;
		this.content = content;
		this.tags = tags;
		this.state = state;
		this.mediaCode = mediaCode;
		this.createdAt = createdAt;
	}
}

package Lookids.Feed.feed.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserKafkaDto {

	private String uuid;

	@Builder
	public UserKafkaDto(String uuid) {
		this.uuid = uuid;
	}
}

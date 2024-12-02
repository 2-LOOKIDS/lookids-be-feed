package Lookids.Feed.feed.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DeleteKafkaDto {
	private String feedCode;

	@Builder
	public DeleteKafkaDto(String feedCode) {
		this.feedCode = feedCode;
	}

	public static DeleteKafkaDto toDto(String feedCode) {
		return DeleteKafkaDto.builder()
			.feedCode(feedCode)
			.build();
	}
}

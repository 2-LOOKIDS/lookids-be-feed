package Lookids.Feed.media.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaRequestDto {

	private String mediaType;
	private String mediaUrl;
	
}

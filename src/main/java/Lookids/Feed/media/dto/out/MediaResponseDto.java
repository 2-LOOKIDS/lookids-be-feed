package Lookids.Feed.media.dto.out;

import Lookids.Feed.media.domain.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaResponseDto {

    private String mediaType;
    private String mediaUrl;

    public static MediaResponseDto toDto(Media media) {
        return MediaResponseDto.builder()
                .mediaType(media.getMediaType())
                .mediaUrl(media.getMediaUrl())
                .build();
    }

}

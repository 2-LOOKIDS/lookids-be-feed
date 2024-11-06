package Lookids.Feed.media.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MediaRequestVo {

	private String mediaType;
	private int mediaOrder;
	private String mediaUrl;
	private Double latitude;
	private Double longitude;

}

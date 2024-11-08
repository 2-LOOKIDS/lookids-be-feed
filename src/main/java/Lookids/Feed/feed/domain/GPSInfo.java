package Lookids.Feed.feed.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@Getter
public class GPSInfo {

	private double latitude;
	private double longitude;

	@Builder
	public GPSInfo(
		double latitude,
		double longitude
	) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}

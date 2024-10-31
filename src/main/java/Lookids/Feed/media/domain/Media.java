package Lookids.Feed.media.domain;

import Lookids.Feed.feed.domain.Feed;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String feedCode;

	@Column(nullable = false)
	private String mediaType;

	@Column(nullable = false)
	private String mediaUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feedId", nullable = false)
	private Feed feed;
}

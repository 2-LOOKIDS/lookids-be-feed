package Lookids.Feed.common.utills;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CursorPage<T> {
	private List<T> content;
	private Long nextCursor;
	private Boolean hasNext;
	private Integer size;
	private Integer page;

	public boolean hasNext() {
		return nextCursor != null;
	}

	@Builder
	public CursorPage(
		List<T> content,
		Long nextCursor,
		Boolean hasNext,
		Integer size,
		Integer page
	) {
		this.content = content;
		this.nextCursor = nextCursor;
		this.hasNext = hasNext;
		this.size = size;
		this.page = page;
	}
}

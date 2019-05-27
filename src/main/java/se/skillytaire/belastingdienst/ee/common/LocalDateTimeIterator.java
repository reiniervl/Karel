package se.skillytaire.belastingdienst.ee.common;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.function.Function;

public class LocalDateTimeIterator implements Iterator<LocalDateTime>{

	private final Function<LocalDateTime, LocalDateTime> incrementor;
	private final LocalDateTime from;
	private final LocalDateTime to;
	
	private LocalDateTime cursor;
	public LocalDateTimeIterator(LocalDateTime from,LocalDateTime to, Function<LocalDateTime, LocalDateTime> incrementor) {
		this.from = from;
		this.to = to;
		this.incrementor = incrementor;
		this.cursor = from;
	}

	@Override
	public boolean hasNext() {
		return cursor.isBefore(to);
	}

	@Override
	public LocalDateTime next() {
		this.cursor = this.incrementor.apply(cursor);
		return cursor;
	}

	
	
	

}

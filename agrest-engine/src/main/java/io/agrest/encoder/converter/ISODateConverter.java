package io.agrest.encoder.converter;

import java.time.Instant;
import java.util.Date;

import static io.agrest.encoder.DateTimeFormatters.isoLocalDate;

public class ISODateConverter extends AbstractConverter {

	private static final StringConverter instance = new ISODateConverter();

	public static StringConverter converter() {
		return instance;
	}

	private ISODateConverter() {
	}

	@Override
	protected String asStringNonNull(Object object) {
		Date date = (Date) object;
		return isoLocalDate().format(Instant.ofEpochMilli(date.getTime()));
	}
}

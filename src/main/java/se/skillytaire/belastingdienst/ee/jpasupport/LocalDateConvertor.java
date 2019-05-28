package se.skillytaire.belastingdienst.ee.jpasupport;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA 2.1 heeft geen LocalDate support.
 */
@Converter(autoApply = true)
public class LocalDateConvertor implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate date) {
		Date result;
		if (date != null) {
			Instant instant = Instant.from(date);
			result = Date.from(instant);
		} else {
			result = null;
		}
		return result;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date value) {
		Instant instant = value.toInstant();
		return LocalDate.from(instant);
	}

}

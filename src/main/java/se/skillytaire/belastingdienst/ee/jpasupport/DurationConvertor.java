package se.skillytaire.belastingdienst.ee.jpasupport;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
/**
 * JPA 2.1 heeft geen Duration support.
 */
@Converter(autoApply = true)
public class DurationConvertor implements AttributeConverter<Duration, Long> {
	@Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration == null ? null : duration.toNanos();
    }
 
    @Override
    public Duration convertToEntityAttribute(Long duration) {
        return duration == null ? null : Duration.of(duration, ChronoUnit.NANOS);
    }
}

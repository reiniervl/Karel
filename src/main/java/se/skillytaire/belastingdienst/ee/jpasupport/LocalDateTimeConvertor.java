package se.skillytaire.belastingdienst.ee.jpasupport;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConvertor implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }
 
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime();
    }

}

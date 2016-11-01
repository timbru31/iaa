package de.nordakademie.iaa_multiple_choice.domain.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * A helper converter to use LocalDate with JPA since there is no out of the box support.
 * 
 * @author Thorben Janssen
 * @see <a href="http://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/">How to persist LocalDate and
 *      LocalDateTime with JPA </a>
 *
 */

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(final LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(final Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}

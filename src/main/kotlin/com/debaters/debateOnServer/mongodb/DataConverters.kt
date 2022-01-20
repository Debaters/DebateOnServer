import org.springframework.core.convert.converter.Converter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

class OffsetDateTimeReadConverter : Converter<Date, OffsetDateTime> {
    override fun convert(source: Date): OffsetDateTime? {
        return source.toInstant().atOffset(ZoneOffset.UTC)
    }
}

class OffsetDateTimeWriteConverter : Converter<OffsetDateTime, Date> {
    override fun convert(source: OffsetDateTime): Date? {
        return Date.from(source.toInstant())
    }
}
package com.gabrielecavallo.brooker.web.graphql.scalars

import com.netflix.graphql.dgs.DgsScalar
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@DgsScalar(name = "DateTime")
class DateTimeScalar : Coercing<LocalDateTime, String> {
    override fun serialize(p0: Any): String {
        if (p0 is LocalDateTime)
            return p0.format(DateTimeFormatter.ISO_DATE_TIME)
        else
            throw CoercingSerializeException()
    }

    override fun parseValue(p0: Any): LocalDateTime {
        if (p0 is StringValue)
            return LocalDateTime.parse(p0.value, DateTimeFormatter.ISO_DATE_TIME)
        else
            throw CoercingParseValueException()
    }

    override fun parseLiteral(p0: Any): LocalDateTime {
        if (p0 is StringValue)
            return LocalDateTime.parse(p0.value, DateTimeFormatter.ISO_DATE_TIME)
        else
            throw CoercingParseLiteralException()
    }
}
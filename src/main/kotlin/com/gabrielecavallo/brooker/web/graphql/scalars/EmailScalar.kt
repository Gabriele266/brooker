package com.gabrielecavallo.brooker.web.graphql.scalars

import com.netflix.graphql.dgs.DgsScalar
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import java.util.regex.Pattern

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

@DgsScalar(name = "Email")
class EmailScalar : Coercing<String, String> {
    override fun serialize(p0: Any): String {
        if (p0 is String)
            return p0
        else
            throw CoercingSerializeException()
    }

    override fun parseValue(p0: Any): String {
        if (p0 is String && EMAIL_ADDRESS_PATTERN.matcher(p0).matches())
            return p0
        else
            throw CoercingParseValueException()
    }

    override fun parseLiteral(p0: Any): String {
        if (p0 is StringValue && EMAIL_ADDRESS_PATTERN.matcher(p0.value).matches())
            return p0.value
        else
            throw CoercingParseLiteralException()
    }
}
package com.gabrielecavallo.brooker.common

import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import org.bson.types.ObjectId
import kotlin.jvm.Throws

/**
 * Converts a string into an object id
 */
@Throws(InvalidIdException::class)
fun stringToObjectId(input: String): ObjectId {
    if (!ObjectId.isValid(input)) throw InvalidIdException(input, "Invalid id string for ObjectId: $input")

    return ObjectId(input)
}
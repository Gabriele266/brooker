package com.gabrielecavallo.brooker.common

import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import org.bson.types.ObjectId
import kotlin.jvm.Throws
import kotlin.random.Random

/**
 * Converts a string into an object id
 */
@Throws(InvalidIdException::class)
fun stringToObjectId(input: String): ObjectId {
    if (!ObjectId.isValid(input)) throw InvalidIdException(input, "Invalid id string for ObjectId: $input")

    return ObjectId(input)
}

fun randomInt(min: Int, max: Int) =
    Random.nextInt(min, max)

fun randomLong(min: Long, max: Long) =
    Random.nextLong(min, max)
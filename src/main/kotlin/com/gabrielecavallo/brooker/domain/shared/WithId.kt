package com.gabrielecavallo.brooker.domain.shared

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Basic entity with an id
 */
@Document
open class WithId(
    @Id
    var id: String = ObjectId.get().toHexString()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WithId

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "WithId(id=$id)"
    }
}
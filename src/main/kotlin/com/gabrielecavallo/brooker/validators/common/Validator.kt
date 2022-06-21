package com.gabrielecavallo.brooker.validators.common

interface Validator<Input> {
    /**
     * Check validity of a single input and return
     * true if it is valid, false otherwise
     */
    fun validateOne(input: Input): Boolean

    /**
     * Validate one input or throw exception
     */
    @kotlin.jvm.Throws(ValidatorException::class)
    fun validateOrThrow(input: Input)
}
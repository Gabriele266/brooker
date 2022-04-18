package com.gabrielecavallo.brooker.services.common

interface Filter<T> {
    fun filter(data: List<T>): List<T>
}
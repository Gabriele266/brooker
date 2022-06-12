package com.gabrielecavallo.brooker.services.s3

import kotlinx.coroutines.Job

interface S3Service {
    /**
     * Upload a file to s3
     */
    suspend fun upload(fileName: String, content: String): Job

    /**
     * Download an object from s3
     */
    fun download(key: String): ByteArray
}
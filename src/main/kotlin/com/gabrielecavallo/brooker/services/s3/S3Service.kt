package com.gabrielecavallo.brooker.services.s3

interface S3Service {
    /**
     * Upload a file to s3
     */
    fun upload(path: String, fileName: String, content: String)

    /**
     * Download an object from s3
     */
    fun download(path: String, key: String): ByteArray
}
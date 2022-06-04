package com.gabrielecavallo.brooker.services.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.util.IOUtils
import org.springframework.stereotype.Service

@Service
class S3ServiceImpl(
    val amazonS3: AmazonS3
) : S3Service {
    override fun upload(path: String, fileName: String, content: String) {
        val result = amazonS3.putObject(path, fileName, content)
    }

    override fun download(path: String, key: String): ByteArray =
        IOUtils.toByteArray(amazonS3.getObject(path, key).objectContent)
}
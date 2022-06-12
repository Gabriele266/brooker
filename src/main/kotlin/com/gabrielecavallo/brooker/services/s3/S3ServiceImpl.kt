package com.gabrielecavallo.brooker.services.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.util.IOUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service

@Service
class S3ServiceImpl(
    val amazonS3: AmazonS3
) : S3Service {
    override suspend fun upload(path: String, fileName: String, content: String) = coroutineScope {
        launch {
            val result = amazonS3.putObject(path, fileName, content)
        }
    }

    override fun download(path: String, key: String): ByteArray =
        IOUtils.toByteArray(amazonS3.getObject(path, key).objectContent)
}
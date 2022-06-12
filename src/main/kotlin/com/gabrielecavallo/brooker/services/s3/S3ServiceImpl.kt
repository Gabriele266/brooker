package com.gabrielecavallo.brooker.services.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.util.IOUtils
import com.gabrielecavallo.brooker.config.BucketConfigData
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service

@Service
class S3ServiceImpl(
    val amazonS3: AmazonS3,
    val bucketConfigData: BucketConfigData
) : S3Service {
    override suspend fun upload(fileName: String, content: String) = coroutineScope {
        launch {
            val result = amazonS3.putObject(bucketConfigData.bucketName, fileName, content)
        }
    }

    override fun download(key: String): ByteArray =
        IOUtils.toByteArray(amazonS3.getObject(bucketConfigData.bucketName, key).objectContent)
}
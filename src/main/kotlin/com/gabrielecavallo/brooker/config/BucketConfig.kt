package com.gabrielecavallo.brooker.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

data class BucketConfigData(val bucketName: String)

@Configuration
class BucketConfig {
    @Bean
    fun bucketConfigData(@Value("\${s3.bucketName}") bucketName: String) = BucketConfigData(bucketName)
}
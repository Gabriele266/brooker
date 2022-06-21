package com.gabrielecavallo.brooker.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@PropertySource("classpath:credentials.properties")
@Configuration
class AmazonConfig {
    @Bean
    fun s3(@Value("\${s3.accessKey}") accessKey: String, @Value("\${s3.secretKey}") secretKey: String): AmazonS3 {
        val credentials: AWSCredentials =
            BasicAWSCredentials(accessKey, secretKey)

        return AmazonS3ClientBuilder.standard().withCredentials(StaticCredentialsProvider(credentials))
            .withRegion("eu-west-2").build()
    }
}
package com.gabrielecavallo.brooker.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonConfig {
    @Bean
    fun s3(): AmazonS3 {
        val credentials: AWSCredentials =
            BasicAWSCredentials("AKIARBJXEYLXHUS7DDW6", "EoDhtktr8UXgX0DfkEFNYv5WcsoNCJ5dWRWFR7HQ")

        return AmazonS3ClientBuilder.standard().withCredentials(StaticCredentialsProvider(credentials))
            .withRegion("eu-west-2").build()
    }
}
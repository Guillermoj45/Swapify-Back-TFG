package com.example.swapify_back.cloudinary

import com.cloudinary.Cloudinary
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CloudinaryConfig {
    @Value("\${CLOUDINARY_CLOUD_NAME}")
    private val cloudName: String? = null

    @Value("\${CLOUDINARY_API_KEY}")
    private val apiKey: String? = null

    @Value("\${CLOUDINARY_API_SECRET}")
    private val apiSecret: String? = null

    @Bean
    fun cloudinary(): Cloudinary {
        val config: MutableMap<String?, String?> = HashMap()
        config["cloud_name"] = cloudName
        config["api_key"] = apiKey
        config["api_secret"] = apiSecret
        return Cloudinary(config)
    }
}

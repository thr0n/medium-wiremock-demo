package com.medium.thr0n.wiremockdemo.service

import com.medium.thr0n.wiremockdemo.configuration.ProductAvailabilityProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException

@Service
class ProductAvailabilityService(val properties: ProductAvailabilityProperties) {
    val restTemplate = RestTemplate()

    fun isAvailable(articleId: String): String? {
        try {
            val response = restTemplate.getForEntity(
                    properties.host + "/external-service/product-availability/" + articleId,
                    String::class.java)
            return response.body
        } catch (e: HttpClientErrorException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}
package com.medium.thr0n.wiremockdemo.controllers

import com.medium.thr0n.wiremockdemo.junit.ProductAvailabilityWiremockExtension
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(ProductAvailabilityWiremockExtension::class)
@ExtendWith(ProductAvailabilityWiremockExtension::class)
internal class ProductAvailabilityControllerIntTest(
        @LocalServerPort val port: Int
) {
    val endpoint = "http://localhost:$port/api/product-availability"
    val restTemplate = TestRestTemplate()

    @Test
    internal fun `Should return HTTP status OK for a valid product id`() {
        val response = restTemplate.exchange(
                "$endpoint/1",
                HttpMethod.GET,
                null,
                String::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    internal fun `Should return HTTP status NOT FOUND for an invalid product id`() {
        val response = restTemplate.exchange(
                "$endpoint/0",
                HttpMethod.GET,
                null,
                String::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }
}
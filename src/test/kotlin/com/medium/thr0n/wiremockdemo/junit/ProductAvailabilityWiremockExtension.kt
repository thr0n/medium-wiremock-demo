package com.medium.thr0n.wiremockdemo.junit

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.medium.thr0n.wiremockdemo.configuration.ProductAvailabilityProperties
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

private val productAvailabilityWiremockServer = WireMockServer(WireMockConfiguration.options().dynamicPort())

@TestConfiguration
class ProductAvailabilityWiremockExtension : BeforeAllCallback, AfterAllCallback {
    val endpoint = "/external-service/product-availability"

    init { productAvailabilityWiremockServer.start() }

    @Bean
    @Primary
    fun productAvailabilityProperties(): ProductAvailabilityProperties {
        val props = ProductAvailabilityProperties()
        props.host = productAvailabilityWiremockServer.baseUrl()
        return props
    }

    override fun beforeAll(context: ExtensionContext?) {
        productAvailabilityWiremockServer.stubFor(
                get(urlPathEqualTo("$endpoint/1"))
                        .willReturn(ok())
        )
        productAvailabilityWiremockServer.stubFor(
                get(urlPathEqualTo("$endpoint/0"))
                        .willReturn(notFound())
        )
    }

    override fun afterAll(context: ExtensionContext?) {
        productAvailabilityWiremockServer.stop()
    }
}

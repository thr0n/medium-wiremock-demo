package com.medium.thr0n.wiremockdemo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("api.productavailabilty")
class ProductAvailabilityProperties {
    lateinit var host: String
}
package com.medium.thr0n.wiremockdemo.controllers

import com.medium.thr0n.wiremockdemo.service.ProductAvailabilityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/product-availability"])
class ProductAvailabilityController(
        val productAvailabilityService: ProductAvailabilityService
) {
    @GetMapping("/{productId}")
    fun isAvailable(@PathVariable productId: String) =
            ResponseEntity.ok().body(productAvailabilityService.isAvailable(productId))
}
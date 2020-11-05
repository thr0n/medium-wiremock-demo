package com.medium.thr0n.wiremockdemo.controllers

import com.medium.thr0n.wiremockdemo.service.ProductAvailabalityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/product-availability"])
class ProductAvailabilityController(
        val productAvailabalityService: ProductAvailabalityService
) {
    @GetMapping("/{productId}")
    fun isAvailable(@PathVariable productId: String) =
            ResponseEntity.ok().body(productAvailabalityService.isAvailable(productId))
}
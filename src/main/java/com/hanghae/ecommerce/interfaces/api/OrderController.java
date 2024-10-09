package com.hanghae.ecommerce.interfaces.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    @Operation(summary = "주문 생성")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody Map<String, Object> orderRequest) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Order placed successfully");

        return ResponseEntity.ok(response);
    }

}

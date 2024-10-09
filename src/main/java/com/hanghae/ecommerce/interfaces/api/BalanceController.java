package com.hanghae.ecommerce.interfaces.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 잔액 조회")
    public ResponseEntity<Map<String, Object>> getUserBalance(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("balance", 10000);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/charge")
    @Operation(summary = "사용자 잔액 충전")
    public ResponseEntity<Map<String, Object>> chargeUserBalance(@RequestBody Map<String, Object> request) {
        String userId = String.valueOf(request.get("userId"));
        Integer amount = (Integer) request.get("amount");

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("newBalance", 10000 + amount);

        return ResponseEntity.ok(response);
    }

}

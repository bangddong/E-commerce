package com.hanghae.ecommerce.interfaces.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "주문 생성 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                {
                  "status": "Order placed successfully"
                }
            """))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content()),
            @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음", content = @Content()),
            @ApiResponse(responseCode = "409", description = "동시성 충돌", content = @Content()),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content())
    })
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody Map<String, Object> orderRequest) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Order placed successfully");

        return ResponseEntity.ok(response);
    }

}

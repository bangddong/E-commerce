package com.hanghae.ecommerce.interfaces.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{userId}/balance")
    @Operation(summary = "사용자 잔액 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "잔액 조회 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                      "userId": "1",
                      "balance": 10000
                    }
                """))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content()),
            @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음", content = @Content()),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content())
    })
    public ResponseEntity<Map<String, Object>> getUserBalance(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("balance", 10000);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/balance")
    @Operation(summary = "사용자 잔액 충전")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "충전 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                      "userId": "1",
                      "newBalance": 11000
                    }
                """))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content()),
            @ApiResponse(responseCode = "404", description = "사용자 찾을 수 없음", content = @Content()),
            @ApiResponse(responseCode = "409", description = "동시성 충돌", content = @Content()),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content())
    })

    public ResponseEntity<Map<String, Object>> chargeUserBalance(@RequestBody Map<String, Object> request) {
        String userId = String.valueOf(request.get("userId"));
        Integer amount = (Integer) request.get("amount");

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("newBalance", 10000 + amount);

        return ResponseEntity.ok(response);
    }

}

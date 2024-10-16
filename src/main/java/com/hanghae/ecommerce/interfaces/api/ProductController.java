package com.hanghae.ecommerce.interfaces.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    @Operation(summary = "상품 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                [
                  {
                    "id": 1,
                    "name": "Product A",
                    "price": 1000,
                    "stock": 50
                  },
                  {
                    "id": 2,
                    "name": "Product B",
                    "price": 2000,
                    "stock": 30
                  }
                ]
            """))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content()),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content())
    })
    public ResponseEntity<List<Map<String, Object>>> getProducts() {
        List<Map<String, Object>> products = new ArrayList<>();

        Map<String, Object> product1 = new HashMap<>();
        product1.put("id", 1);
        product1.put("name", "Product A");
        product1.put("price", 1000);
        product1.put("stock", 50);

        Map<String, Object> product2 = new HashMap<>();
        product2.put("id", 2);
        product2.put("name", "Product B");
        product2.put("price", 2000);
        product2.put("stock", 30);

        products.add(product1);
        products.add(product2);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/top-sellers")
    @Operation(summary = "판매량 상위 상품 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "판매량 상위 상품 조회 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                [
                  {
                    "id": 1,
                    "name": "Product A",
                    "sold": 100
                  }
                ]
            """))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content()),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content())
    })
    public ResponseEntity<List<Map<String, Object>>> getTopSellers() {
        List<Map<String, Object>> topSellers = new ArrayList<>();

        Map<String, Object> product = new HashMap<>();
        product.put("id", 1);
        product.put("name", "Product A");
        product.put("sold", 100);

        topSellers.add(product);

        return ResponseEntity.ok(topSellers);
    }

}

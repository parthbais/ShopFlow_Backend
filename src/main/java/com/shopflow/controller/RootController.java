package com.shopflow.controller;

import com.shopflow.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Map<String, String>>> welcome() {
        return ResponseEntity.ok(ApiResponse.success("Welcome to ShopFlow API", 
            Map.of(
                "status", "UP",
                "docs", "/swagger-ui/index.html",
                "version", "v1"
            )
        ));
    }
}

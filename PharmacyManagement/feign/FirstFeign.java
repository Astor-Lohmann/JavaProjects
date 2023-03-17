package com.example.PharmacyManagement.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url="http://localhost:8000", name = "hello")
public interface FirstFeign {
    @GetMapping("hello")
    String hello();
}

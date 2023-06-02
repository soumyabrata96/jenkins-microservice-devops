package com.microservice.couponservice.controller;

import com.microservice.couponservice.dto.CouponResponse;
import com.microservice.couponservice.entity.Coupon;
import com.microservice.couponservice.environment.InstanceInformationService;
import com.microservice.couponservice.repo.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    @Autowired
    CouponRepo couponRepo;
    @Autowired
    InstanceInformationService instanceInformationService;

    @PostMapping("/create")
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody Coupon coupon){
        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        headers.put("Correlation-Id", List.of(UUID.randomUUID().toString()));
        headers.put("TimeStamp", List.of(LocalDateTime.now().toString()));
        Coupon newCoupon=couponRepo.save(coupon);
        CouponResponse couponResponse=CouponResponse.builder().id(newCoupon.getId()).code(newCoupon.getCode())
                .discount(newCoupon.getDiscount()).expDate(newCoupon.getExpDate())
                .serviceInstance(instanceInformationService.retrieveInstanceInfo()).build();
        return new ResponseEntity<>(couponResponse,headers,HttpStatus.CREATED);
    }
    @GetMapping("/retrieve/{code}")
    public ResponseEntity<CouponResponse> getCouponByCode(@PathVariable String code){
        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        headers.put("Correlation-Id", List.of(UUID.randomUUID().toString()));
        headers.put("TimeStamp", List.of(LocalDateTime.now().toString()));
        Coupon newCoupon=couponRepo.findByCode(code).orElseThrow();
        CouponResponse couponResponse=CouponResponse.builder().id(newCoupon.getId()).code(newCoupon.getCode())
                .discount(newCoupon.getDiscount()).expDate(newCoupon.getExpDate())
                .serviceInstance(instanceInformationService.retrieveInstanceInfo()).build();
        return new ResponseEntity<>(couponResponse,headers,HttpStatus.OK);
    }
}

package com.microservice.couponservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponResponse {
    private Long id;
    private String code;
    private BigDecimal discount;
    private LocalDate expDate;
    private String serviceInstance;
}

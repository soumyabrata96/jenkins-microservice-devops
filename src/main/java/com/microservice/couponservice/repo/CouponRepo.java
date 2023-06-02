package com.microservice.couponservice.repo;

import com.microservice.couponservice.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon,Long> {
   Optional<Coupon> findByCode(String code);
}

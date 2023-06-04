package com.microservice.couponservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.microservice.couponservice.dto.CouponResponse;
import com.microservice.couponservice.entity.Coupon;
import com.microservice.couponservice.environment.InstanceInformationService;
import com.microservice.couponservice.repo.CouponRepo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CouponController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CouponControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CouponRepo couponRepo;
    Coupon coupon;
    private ObjectMapper objectMapper=new ObjectMapper();
    @MockBean
    InstanceInformationService instanceInformationService;

    @BeforeEach
    void initialize(){
        coupon=new Coupon();
        coupon.setCode("SUPERSALE");
        coupon.setDiscount(BigDecimal.valueOf(10));
        coupon.setExpDate(LocalDate.of(2023,6,30));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

    }
    @Test
    void createCoupon_test() throws Exception {
        Coupon coupon1=new Coupon();
        coupon1.setId(1L);
        coupon1.setCode(coupon.getCode());
        coupon1.setDiscount(coupon.getDiscount());
        coupon1.setExpDate(coupon.getExpDate());
        when(couponRepo.save(coupon)).thenReturn(coupon1);
        mockMvc.perform(post("/coupons/create")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(coupon))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.discount", Matchers.equalTo(10)));
    }
    @Test
    void getCoupon_test() throws Exception {
        Coupon coupon1=new Coupon();
        coupon1.setId(1L);
        coupon1.setCode(coupon.getCode());
        coupon1.setDiscount(coupon.getDiscount());
        coupon1.setExpDate(coupon.getExpDate());
        when(couponRepo.findByCode(anyString())).thenReturn(Optional.of(coupon1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/coupons/retrieve/{code}"
                        ,"SUPERSALE").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(jsonPath("$.discount", Matchers.equalTo(10)));
    }
}

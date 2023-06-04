package com.microservice.couponservice;

import com.microservice.couponservice.entity.Coupon;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CouponServiceApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;
	@Value("${spring.security.user.name}")
	private String username;
	@Value("${spring.security.user.password}")
	private String password;

	@Test
	void contextLoads() throws JSONException {
		Coupon coupon=new Coupon();
		coupon.setCode("BUMPERSALE");
		coupon.setDiscount(BigDecimal.valueOf(50));
		coupon.setExpDate(LocalDate.of(2023,6,30));
		TestRestTemplate testRestTemplate1=testRestTemplate.withBasicAuth(username,password);
		testRestTemplate1.postForObject("/coupons/create",coupon, String.class);
		String coupon1=testRestTemplate1.getForObject("/coupons/retrieve/{code}", String.class,"BUMPERSALE");
		assertEquals("{code:BUMPERSALE,discount:50,expDate:2023-06-30}",coupon1,false);
	}

}

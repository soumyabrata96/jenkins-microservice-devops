package com.microservice.couponservice.repo;

import com.microservice.couponservice.entity.User;

public interface UserRepo {
    public User findByEmail(String email);
}

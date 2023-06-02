package com.microservice.couponservice.entity;

import jakarta.persistence.*;

import java.util.Set;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

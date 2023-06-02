package com.microservice.couponservice.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Component
public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user=userRepo.findByEmail(email);
//        System.out.println(user.toString());
//        Set<GrantedAuthority> authoritySet=user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authoritySet);
        return null;
    }
}

package com.codegym.service;

import com.codegym.entity.UserEntity;
import com.codegym.request.CreateUserRequest;
import com.codegym.request.UpdateUserRequest;
import com.codegym.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> findAll(Pageable pageable);

    Page<UserResponse> findAllByFirstNameContaining(String firstname, Pageable pageable);

    UserResponse findById(Long id);

    void create(CreateUserRequest userRequest);

    void update(UpdateUserRequest updateUserRequest);

    void remove(Long id);

    boolean existsByEmail(String email);
}
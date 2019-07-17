package com.codegym.convert;

import com.codegym.entity.UserEntity;
import com.codegym.request.CreateUserRequest;
import com.codegym.request.UpdateUserRequest;
import com.codegym.response.UserResponse;

public class UserConverter {

    public static UserEntity convertUserRequestToUserEntity(CreateUserRequest userRequest) {
        return UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .age(userRequest.getAge())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
    }

    public static UserResponse convertUserEntityToUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .age(userEntity.getAge())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }

    public static UserEntity convertUserRequestToUserEntity(UpdateUserRequest userRequest) {
        return UserEntity.builder()
                .id(userRequest.getId())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .age(userRequest.getAge())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
    }

}
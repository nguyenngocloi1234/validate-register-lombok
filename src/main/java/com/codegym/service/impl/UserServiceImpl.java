package com.codegym.service.impl;

import com.codegym.convert.UserConverter;
import com.codegym.entity.UserEntity;
import com.codegym.repository.UserRepository;
import com.codegym.request.CreateUserRequest;
import com.codegym.request.UpdateUserRequest;
import com.codegym.response.UserResponse;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(c->UserConverter.convertUserEntityToUserResponse(c));
    }

    @Override
    public Page<UserResponse> findAllByFirstNameContaining(String firstname, Pageable pageable) {
        return userRepository.findAllByFirstNameContaining(firstname,pageable).map(c->UserConverter.convertUserEntityToUserResponse(c));
    }

    @Override
    public UserResponse findById(Long id) {
        return UserConverter.convertUserEntityToUserResponse(userRepository.findOne(id));
    }

    @Override
    public void create(CreateUserRequest userRequest) {
        userRepository.save(UserConverter.convertUserRequestToUserEntity(userRequest));
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        userRepository.save(UserConverter.convertUserRequestToUserEntity(updateUserRequest));

    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


//    public void convertMapJava8() {
//        List<UserEntity> userEntities = new ArrayList<>();
//        userEntities.stream().map(UserConverter::convertUserEntityToUserResponse).collect(Collectors.toList());
//    }
}
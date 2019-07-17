package com.codegym.repository;

import com.codegym.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
    Page<UserEntity> findAllByFirstNameContaining(String firstname, Pageable pageable);

}
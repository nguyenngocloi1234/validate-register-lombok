package com.codegym.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
//@NoArgsConstructor
public class UserResponse {


    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    Integer age;
    String email;
}
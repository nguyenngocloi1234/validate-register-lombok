package com.codegym.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Component;
import javax.validation.constraints.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CreateUserRequest {
    @NotEmpty
    @Size(max = 255)
    String firstName;

    @NotEmpty
    @Size(max = 255)
    String lastName;

    @NotEmpty
    @Size(max = 255)
    String phoneNumber;

    @NotNull
    @Min(18)
    @Max(150)
    Integer age;

    @NotEmpty
    @Size(max = 255)
    @Email
    String email;
}
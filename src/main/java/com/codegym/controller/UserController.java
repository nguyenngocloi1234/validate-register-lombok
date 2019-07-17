package com.codegym.controller;

import com.codegym.convert.UserConverter;
import com.codegym.entity.UserEntity;
import com.codegym.request.CreateUserRequest;
import com.codegym.request.UpdateUserRequest;
import com.codegym.response.UserResponse;
import com.codegym.service.UserService;
import com.codegym.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @GetMapping("/users")
    public ModelAndView listUsers(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<UserResponse> users;
        if(s.isPresent()){
            users = userService.findAllByFirstNameContaining(s.get(),pageable);
        } else {
            users = userService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("userResquest", new CreateUserRequest());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView checkValidation(@Valid @ModelAttribute("userResquest") CreateUserRequest userRequest, BindingResult bindingResult) {
        userValidator.validate(userRequest, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/user/create");
            modelAndView.addObject("user", userRequest);
            return modelAndView;
        } else {
            userService.create(userRequest);
            ModelAndView modelAndView = new ModelAndView("/user/result");
            modelAndView.addObject("message", "New customer created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        UserResponse userResponse = userService.findById(id);
        if (userResponse != null) {
            ModelAndView modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("user", userResponse);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateUser(@PathVariable Long id,@ModelAttribute("user") UpdateUserRequest updateUserRequest){
        userService.update(updateUserRequest);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", updateUserRequest);
        modelAndView.addObject("message","Customer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        UserResponse user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/delete");
            modelAndView.addObject("user", user);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }


    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("user") UserEntity userEntity){
        userService.remove(userEntity.getId());
        return "redirect:users";
    }


}
package com.labanovich.user.controller;

import com.labanovich.user.dto.UserCreateDto;
import com.labanovich.user.dto.UserDto;
import com.labanovich.user.entity.User;
import com.labanovich.user.exception.EntityNotFoundException;
import com.labanovich.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> findById(@PathVariable Long id) {
        log.info("id {}", id);
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(() -> new EntityNotFoundException("id", User.class, id)));
    }

    @PostMapping
    public Mono<ResponseEntity<UserDto>> save(@RequestBody Mono<UserCreateDto> userCreateDto) {
        return userService.save(userCreateDto)
                .map(userDto -> ResponseEntity.status(HttpStatus.CREATED).body(userDto));
    }
}

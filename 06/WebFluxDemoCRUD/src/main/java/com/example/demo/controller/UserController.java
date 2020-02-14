package com.example.demo.controller;

import com.example.demo.webflux.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/user")
public class UserController {
    Map<Long, User> users = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        users.put(1L, new User(1, "xiegudong", 22));
        users.put(2L, new User(2, "xieshenghao", 2));
    }

    // 获取所有用户
    @GetMapping("/list")
    public Flux<User> getAll() {
        return Flux.fromIterable(users.entrySet().stream()
                .map(entry -> entry.getValue())
        .collect(Collectors.toList()));
    }

    // 获取单个用户
    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable Long id) {
        return Mono.justOrEmpty(users.get(id));
    }

    // 创建用户
    @PostMapping("")
    public Mono<ResponseEntity<String>> addUser(User user) {
        users.put(user.getId(), user);
        return Mono.just(new ResponseEntity<>("添加成功", HttpStatus.CREATED));
    }

    // 修改用户
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> putUser(@PathVariable Long id,
                                              User user) {
        user.setId(id);
        users.put(id, user);
        return Mono.just(new ResponseEntity<>(user, HttpStatus.CREATED));
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteUser(@PathVariable Long id) {
        users.remove(id);
        return Mono.just(new ResponseEntity<>("删除成功", HttpStatus.ACCEPTED));
    }
}

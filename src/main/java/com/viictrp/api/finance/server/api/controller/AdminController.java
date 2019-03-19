package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IUserService;
import com.viictrp.api.finance.server.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final IUserService userService;

    @Autowired
    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findUsers() {
        List<User> users = userService.buscarUsuarios();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") Long id) {
        User user = userService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(user);
    }
}

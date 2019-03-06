package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.IUserService;
import com.viictrp.api.finance.server.api.domain.User;
import com.viictrp.api.finance.server.api.oauth.model.OAuthUser;
import com.viictrp.api.finance.server.api.oauth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private IUserService userService;

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

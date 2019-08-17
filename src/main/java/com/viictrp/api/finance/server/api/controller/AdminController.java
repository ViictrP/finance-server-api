package com.viictrp.api.finance.server.api.controller;

import com.viictrp.api.finance.server.api.business.interfaces.IUserService;
import com.viictrp.api.finance.server.api.domain.Usuario;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin")
@CrossOrigin("*")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final IUserService userService;

    @Autowired
    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<Usuario>> findUsers() {
        List<Usuario> usuarios = userService.buscarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Usuario> findUser(@PathVariable ObjectId id) {
        Usuario usuario = userService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }
}

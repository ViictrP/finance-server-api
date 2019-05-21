package com.viictrp.api.finance.server.api.converter;

import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<Usuario, UserDTO> {

    @Override
    public Usuario toEntity(UserDTO userDTO) {
        Usuario usuario = null;
        if (userDTO != null) {
            usuario = new Usuario();
            usuario.setAge(userDTO.getAge());
            usuario.setEmail(userDTO.getEmail());
            usuario.setLastname(userDTO.getLastname());
            usuario.setName(userDTO.getName());
        }
        return usuario;
    }

    @Override
    public UserDTO toDto(Usuario usuario) {
        UserDTO dto = new UserDTO();
        if (usuario != null) {
            dto = new UserDTO();
            dto.setId(usuario.getId());
            dto.setAge(usuario.getAge());
            dto.setEmail(usuario.getEmail());
            dto.setName(usuario.getName());
            dto.setLastname(usuario.getLastname());
        }
        return dto;
    }
}

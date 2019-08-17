package com.viictrp.api.finance.server.api.converter.user;

import com.viictrp.api.finance.server.api.converter.Converter;
import com.viictrp.api.finance.server.api.domain.Usuario;
import com.viictrp.api.finance.server.api.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<Usuario, UsuarioDTO> {

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = null;
        if (usuarioDTO != null) {
            usuario = new Usuario();
            usuario.setAge(usuarioDTO.getAge());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setLastname(usuarioDTO.getLastname());
            usuario.setName(usuarioDTO.getName());
        }
        return usuario;
    }

    @Override
    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        if (usuario != null) {
            dto = new UsuarioDTO();
            dto.setId(usuario.getId().toString());
            dto.setAge(usuario.getAge());
            dto.setEmail(usuario.getEmail());
            dto.setName(usuario.getName());
            dto.setLastname(usuario.getLastname());
        }
        return dto;
    }
}

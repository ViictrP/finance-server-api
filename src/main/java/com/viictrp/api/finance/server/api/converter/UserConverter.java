package com.viictrp.api.finance.server.api.converter;

import com.viictrp.api.finance.server.api.domain.User;
import com.viictrp.api.finance.server.api.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDTO> {

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = null;
        if (userDTO != null) {
            user = new User();
            user.setAge(userDTO.getAge());
            user.setEmail(userDTO.getEmail());
            user.setLastname(userDTO.getLastname());
            user.setName(userDTO.getName());
        }
        return user;
    }

    @Override
    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        if (user != null) {
            dto = new UserDTO();
            dto.setId(user.getId());
            dto.setAge(user.getAge());
            dto.setEmail(user.getEmail());
            dto.setName(user.getName());
            dto.setLastname(user.getLastname());
        }
        return dto;
    }
}

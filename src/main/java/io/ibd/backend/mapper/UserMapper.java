package io.ibd.backend.mapper;

import io.ibd.backend.model.User;
import io.ibd.backend.model.dto.UserDto;
import io.ibd.backend.model.dto.UserPostDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto user);

    UserDto postDtoToDto(UserPostDto userPostDto);

    default User postDtoToEntity(UserPostDto userPostDto) {
        UserDto userDto = postDtoToDto(userPostDto);

        return dtoToEntity(userDto);
    }
}

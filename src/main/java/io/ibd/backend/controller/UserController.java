package io.ibd.backend.controller;

import io.ibd.backend.mapper.AlertMapper;
import io.ibd.backend.mapper.OpinionMapper;
import io.ibd.backend.mapper.UserMapper;
import io.ibd.backend.model.Alert;
import io.ibd.backend.model.Opinion;
import io.ibd.backend.model.User;
import io.ibd.backend.model.dto.AlertDto;
import io.ibd.backend.model.dto.OpinionDto;
import io.ibd.backend.model.dto.UserDto;
import io.ibd.backend.model.dto.UserPostDto;
import io.ibd.backend.service.AlertService;
import io.ibd.backend.service.OpinionService;
import io.ibd.backend.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final AlertService alertService;
    @Autowired
    private final OpinionService opinionService;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final AlertMapper alertMapper = Mappers.getMapper(AlertMapper.class);
    private final OpinionMapper opinionMapper = Mappers.getMapper(OpinionMapper.class);

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users = userService.getAll();

        return ok(users.stream()
                        .map(userMapper::entityToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(
            value = "/{userLogin}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> getUserById(@PathVariable String userLogin){
        User user = userService.getById(userLogin);

        return ok(userMapper.entityToDto(user));
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> createUser(@RequestBody UserPostDto userPostDto) {
        User user = userMapper.postDtoToEntity(userPostDto);
        User createdUser = userService.create(user);

        return ok(userMapper.entityToDto(createdUser));
    }

    @PutMapping(
            value = "/update/{userLogin}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> updateUser(@PathVariable String userLogin, @RequestBody UserPostDto userPostDto) {
        User user = userMapper.postDtoToEntity(userPostDto);
        User updatedUser = userService.update(userLogin, user);

        return ok(userMapper.entityToDto(updatedUser));
    }

    @DeleteMapping(
            value = "/delete/{userLogin}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> updateUser(@PathVariable String userLogin) {
        User deletedUser = userService.delete(userLogin);

        return ok(userMapper.entityToDto(deletedUser));
    }

    @GetMapping(
            value = "/{userLogin}/opinions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<OpinionDto>> getOpinionsForUser(@PathVariable String userLogin){
        List<Opinion> opinions = opinionService.getAllByUserId(userLogin);

        return ok(opinions.stream()
                .map(opinionMapper::entityToDto)
                .collect(Collectors.toList())
        );
    }
}

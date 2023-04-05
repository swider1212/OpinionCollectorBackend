package io.ibd.backend.service;

import io.ibd.backend.model.User;
import io.ibd.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<String, User> {

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    protected Class<User> getType() {
        return User.class;
    }
}

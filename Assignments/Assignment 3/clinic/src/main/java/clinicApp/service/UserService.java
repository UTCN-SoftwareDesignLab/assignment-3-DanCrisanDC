package clinicApp.service;

import clinicApp.dto.UserDto;
import clinicApp.model.User;

import java.util.List;

public interface UserService {

    boolean delete(int id);

    void update(UserDto userDto);

    boolean create(UserDto userDto);

    List<User> getAll();

    User findByUsernameAndPassword(String username, String password);

    User findByName(String name);
}

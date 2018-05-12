package unit;

import clinicApp.dto.UserDto;
import clinicApp.model.Role;
import clinicApp.model.User;
import clinicApp.repository.UserRepository;
import clinicApp.service.UserService;
import clinicApp.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @Before
    public void setup() {
        this.userService = new UserServiceImpl(userRepository);

        User user1 = new User("User1", "test00", "Parola00", Role.DOCTOR);
        User user2 = new User("User2", "test01", "Parola01", Role.SECRETARY);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userRepository.save(user1)).thenReturn(user1);

    }

    @Test
    public void findAll(){
        List<User> users = userService.getAll();
        Assert.assertEquals(users.size(), 2);
    }

    @Test
    public void create() {
        UserDto user = new UserDto();
        user.setPassword("Parola99");
        Assert.assertNotNull(userService.createUser(user));
    }

    @Test
    public void deleteAll() {
        UserDto userDto = new UserDto();
        userDto.setPassword("Parola99");
        User user = userService.createUser(userDto);
        int id = user.getId();
        userService.deleteAll();

        Assert.assertFalse(userService.findById(id));
    }
}
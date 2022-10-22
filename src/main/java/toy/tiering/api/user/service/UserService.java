package toy.tiering.api.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import toy.tiering.api.user.dto.UserDto;

public interface UserService {

    boolean checkChangeableAuthority(long userId, int authorityCode);


    UserDto.UserResponse getMyInfo();


    void deleteUser(UserDto.UserResponse dto);

}
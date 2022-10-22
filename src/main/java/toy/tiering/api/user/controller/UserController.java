package toy.tiering.api.user.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.tiering.api.user.dto.UserDto;
import toy.tiering.api.user.service.UserService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto.UserResponse> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser() {

        var dto = UserDto.userDelete.deleteUserId();

        userService.deleteUser(dto);

        return ResponseEntity.ok("회원 탈퇴가 성공적으로 완료되었습니다. 그 동안 이용해주셔서 감사합니다.");
    }


}
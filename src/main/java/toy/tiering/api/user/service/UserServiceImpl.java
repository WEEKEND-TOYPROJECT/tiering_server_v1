package toy.tiering.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.tiering.api.common.util.SecurityUtil;
import toy.tiering.api.user.domain.User;
import toy.tiering.api.user.dto.UserDto;
import toy.tiering.api.user.repository.UserRepository;

import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public UserDto.UserResponse getMyInfo() {
        return repository.findById(SecurityUtil.getCurrentUserId())
                .map(UserDto.UserResponse::new)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


    @Override
    public boolean checkChangeableAuthority(long userId, int authorityCode) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new InvalidParameterException("해당 id의 유저가 존재하지 않습니다."));
        return authorityCode >= user.getAuthority().getCode();
    }


    @Override
    public void deleteUser(UserDto.UserResponse dto) {
        Long userId = dto.getUserId();
        repository.deleteById(userId);
    }


}
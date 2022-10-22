package toy.tiering.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import toy.tiering.api.common.util.SecurityUtil;
import toy.tiering.api.oauth.domain.ProviderType;
import toy.tiering.api.user.domain.Authority;
import toy.tiering.api.user.domain.User;

import javax.validation.constraints.*;
import java.security.InvalidParameterException;

public class UserDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserRequest {

        private Long userId;

        private String nickName;

        private Authority authority;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        private Long userId;
        private String username;
        private String nickname;
        private ProviderType providerType;

        public UserResponse(User user) {
            this.userId = user.getUserId();
            this.username = user.getUserName();
            this.nickname = user.getNickName();
            this.providerType = user.getProviderType();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class userInfoModify {
        private String nickname;

        public UserDto.UserRequest changeUserInfo() {
            Long currentUserId = SecurityUtil.getCurrentUserId();
            return UserRequest.builder()
                    .userId(currentUserId)
                    .nickName(nickname)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class userDelete {
        public static UserDto.UserResponse deleteUserId() {
            Long currentUserId = SecurityUtil.getCurrentUserId();
            return UserResponse.builder()
                    .userId(currentUserId)
                    .build();
        }
    }


}
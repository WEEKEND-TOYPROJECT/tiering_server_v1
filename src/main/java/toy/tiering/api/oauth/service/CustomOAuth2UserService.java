package toy.tiering.api.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import toy.tiering.api.oauth.domain.ProviderType;
import toy.tiering.api.oauth.domain.UserPrincipal;
import toy.tiering.api.oauth.domain.info.OAuth2UserInfo;
import toy.tiering.api.oauth.domain.info.OAuth2UserInfoFactory;
import toy.tiering.api.oauth.exception.OAuthProviderMissMatchException;
import toy.tiering.api.user.domain.Authority;
import toy.tiering.api.user.domain.User;
import toy.tiering.api.user.repository.UserRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        userRequest.getClientRegistration().getRegistrationId();
        userRequest.getClientRegistration().getProviderDetails().getTokenUri();
        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType =
                ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());

        Optional<User> userOptional = userRepository.findByUserName(userInfo.getEmail());
        User users;
        if (userOptional.isPresent()) {//회원가입 o
            users = userOptional.get();
            if (providerType != users.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + users.getProviderType() + " account to login."
                );
            }
                updateUser(users, userInfo);
//            AuthMail authMail = mailAuthRepository.findByAuthMailEquals(users.getUsername());
//            if (authMail.getAuthStatus().equals(AuthMail.AuthStatus.CHECKED)) {
//            } else{
//                throw new BoraException(ErrorCode.CHECKED_USER_AUTH_EMAIL, "이메일 인증을 먼저 해주세요.");
//            }
        } else {//회원가입 x
            users = createUser(userInfo, providerType);
        }


        return UserPrincipal.create(users, user.getAttributes());

    }

    // 첫 로그인시 회원 가입
    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        User user = User.builder()
                .userName(userInfo.getEmail())
                .nickName(userInfo.getName())
                .providerType(providerType)
                .authority(Authority.ROLE_USER)
                .oauthId(userInfo.getId())
                .build();
        return userRepository.save(user);
    }

    private User updateUser(User user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getUserName().equals(userInfo.getEmail())) {
            user.setUsername(userInfo.getEmail());
        }
        return user;
    }
}
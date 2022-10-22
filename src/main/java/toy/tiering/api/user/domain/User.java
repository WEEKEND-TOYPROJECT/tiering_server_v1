package toy.tiering.api.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import toy.tiering.api.oauth.domain.ProviderType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="users")
@Slf4j
@Audited(withModifiedFlag = true)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="username", nullable = false, length = 50)
    private String userName;
    @Column(name = "nickname", nullable = true, length = 20)
    private String nickName;

    @Column(name="authority")
    @Enumerated(EnumType.ORDINAL) // enum 문자열 자체가 저장(USER, ADMIN 등)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;


    private String oauthId;


    public void changeNickname(String nickName) {
        this.nickName = nickName;
    }
    public void setUsername(String username) {
        this.userName = username;
    }
}

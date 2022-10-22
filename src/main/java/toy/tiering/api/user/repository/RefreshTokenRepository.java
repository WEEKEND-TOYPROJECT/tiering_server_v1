package toy.tiering.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toy.tiering.api.user.domain.RefreshToken;

import java.sql.Ref;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByValue(String value);
    RefreshToken findByKey(String key);

    String deleteBykey(String key);

//    RefreshToken findByUserIdAndRefreshToken(String userId, String refreshToken);
}
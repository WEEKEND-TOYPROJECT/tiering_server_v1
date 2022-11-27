package toy.tiering.api.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import toy.tiering.api.myteam.domain.SportType;
import toy.tiering.api.player.domain.Player;
import toy.tiering.api.player.repository.PlayerRepository;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void insertTodo() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            Player player = Player.builder()
                    .playerName("박지성" + i)
                    .playerPhoto("photourl")
                    .playerPosition("미드필더" + i)
                    .playerStat("8" + i)
                    .playerSeason("201" + i)
                    .playerNation("대한민국" + i)
                    .playerTeam("맨체스터 유나이티드" + i)
                    .playerTeamLogo("teamLogo")
                    .sportType(SportType.EPL)
                    .build();

            playerRepository.save(player);

        });
    }


    //queryDsl 테스트
    @Test
    public void testQuery1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("playerId").descending());

        Page<Object[]> result = playerRepository.searchPage("t", "멘체스터", pageable);
    }
}

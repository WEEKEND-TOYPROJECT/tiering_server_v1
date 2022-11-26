package toy.tiering.api.player.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.tiering.api.player.domain.Player;
import toy.tiering.api.player.dto.PlayerDto;
import toy.tiering.api.player.repository.PlayerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Transactional
    public void save(PlayerDto.Req playerReqDto) {
        Player player = playerReqDto.toEntity();

        playerRepository.save(player);

    }

    public PlayerDto.Res readPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new IllegalArgumentException("선수를 찾을 수 업습니다."));

        return new PlayerDto.Res(player);
    }


    @Transactional
    public void modifyPlayer(Long playerId, PlayerDto.Req playerReqDto) {
        Player player = playerRepository.getById(playerId);

        player.changePlayer(playerReqDto.getPlayerName(),
                            playerReqDto.getPlayerPhoto(),
                            playerReqDto.getPlayerPosition(),
                            playerReqDto.getPlayerStat(),
                            playerReqDto.getPlayerSeason(),
                            playerReqDto.getPlayerNation(),
                            playerReqDto.getPlayerTeam(),
                            playerReqDto.getPlayerTeamLogo());
    }

    @Transactional
    public void removeMyTeam(Long playerId) {
        playerRepository.deleteById(playerId);
    }

}

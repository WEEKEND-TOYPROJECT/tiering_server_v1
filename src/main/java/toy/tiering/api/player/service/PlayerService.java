package toy.tiering.api.player.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.dto.MyTeamDto;
import toy.tiering.api.myteam.dto.PageRequestDto;
import toy.tiering.api.myteam.dto.PageResultDto;
import toy.tiering.api.player.domain.Player;
import toy.tiering.api.player.dto.PlayerDto;
import toy.tiering.api.player.repository.PlayerRepository;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;


    public Page<Player> playerList(String keyword, Pageable pageable) {

        Page<Player> playerList = playerRepository.findByPlayerTeamContaining(keyword, pageable);

        return playerList;
    }

    @Transactional
    public void save(PlayerDto.Req playerReqDto) {
        Player player = playerReqDto.toEntity();

        playerRepository.save(player);

    }

}
